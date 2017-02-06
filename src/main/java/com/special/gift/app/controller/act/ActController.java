package com.special.gift.app.controller.act;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.special.gift.app.controller.ui.UiController;
import com.special.gift.app.domain.User;
import com.special.gift.app.domain.Vendor;
import com.special.gift.app.domain.VendorId;
import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;
import com.special.gift.app.dto.UserDto;
import com.special.gift.app.dto.VendorDto;
import com.special.gift.app.service.ListingService;
import com.special.gift.app.service.SequenceService;
import com.special.gift.app.service.UserService;
import com.special.gift.app.service.VendorService;
import com.special.gift.app.util.SequenceUtil;
import com.special.gift.app.util.exception.DataAlreadyExistException;

@Controller
@RequestMapping(value = ActController.BASE_PATH)
public class ActController {

  private static final Logger log = LoggerFactory.getLogger(ActController.class);

  public static final String BASE_PATH = "/";

  private static final String USER_ACT_PATH = "user";
  private static final String USER_UPDATE_ACT_PATH = "user-update";

  private static final String VENDOR_ACT_PATH = "vendor";
  private static final String VENDOR_UPDATE_ACT_PATH = "vendor-update";

  // booking
  public static final String BOOKING_REQUEST = "booking/request";
  public static final String BOOKING_PAYMENT = "booking/payment";



  @Inject
  private UserService userService;

  @Inject
  private VendorService vendorService;

  @Inject
  private SequenceService sequenceService;

  @Inject
  private ListingService listingService;



  @PostMapping(value = USER_ACT_PATH)
  public String addNewuser(@ModelAttribute UserDto user, RedirectAttributes redirectAttributes) {
    log.debug("user dto : {}", user.toString());
    try {
      final User userData = new User();
      BeanUtils.copyProperties(user, userData);
      userData.setUserId(sequenceService.generateSequence(SequenceUtil.USER_ID_SEQ));
      userService.insertUser(userData);
    } catch (final Exception exception) {
      log.error("an error occured with message {}", exception.getMessage());
      if (exception instanceof DataAlreadyExistException) {
        final Map<String, String> data = ((DataAlreadyExistException) exception).getExistEntity();
        redirectAttributes.addFlashAttribute("existKey", data.keySet().toArray()[0]);
        redirectAttributes.addFlashAttribute("existValue", data.get(data.keySet().toArray()[0]));
        return "redirect:/notification";
      }
      return "redirect:/register";
    }
    return "redirect:/";
  }

  @PostMapping(value = USER_UPDATE_ACT_PATH)
  public String updateUser(@ModelAttribute UserDto user) {
    log.debug("existing user data : {}", user.toString());
    try {

      final User userEntity = userService.findUserById(user.getUserId());

      if (user != null && user.getUserId() != null) {

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
          userEntity.setEmail(user.getEmail());
        }

        if (user.getHandphone() != null && !user.getHandphone().isEmpty()) {
          userEntity.setHandphone(user.getHandphone());
        }

        if (user.getPhone() != null && !user.getPhone().isEmpty()) {
          userEntity.setPhone(user.getPhone());
        }

        if (user.getUserAddress() != null && !user.getUserAddress().isEmpty()) {
          userEntity.setUserAddress(user.getUserAddress());
        }

        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
          userEntity.setUsername(user.getUsername());
        }

        if (user.getUserZip() != null && !user.getUserZip().isEmpty()) {
          userEntity.setUserZip(user.getUserZip());
        }

      }

      userService.updateUser(userEntity);
      return "redirect:/user-view";
    } catch (final Exception exception) {
      exception.printStackTrace();
      return "redirect:/";
    }
  }


  @PostMapping(value = VENDOR_ACT_PATH)
  public String addNewVendor(@ModelAttribute VendorDto vendor, HttpSession session) {
    log.debug("vendor dto : {}", vendor.toString());
    try {
      vendor.setUser(session.getAttribute("user").toString());
      final User user = userService.findUserByPrincipal(vendor.getUser());
      if (user != null) {
        log.debug("user : {}", user.toString());
        Vendor target = null;
        VendorId vendorId = null;
        final String vendorSequenceId =
            sequenceService.generateSequence(SequenceUtil.VENDOR_ID_SEQ);

        for (final String type : vendor.getVendorType().split(",")) {
          target = new Vendor();

          vendorId = new VendorId();
          vendorId.setType(type);
          vendorId.setVendorId(vendorSequenceId);

          BeanUtils.copyProperties(vendor, target);

          target.setVendorId(vendorId);
          target.setUser(user);

          vendorService.addNewUserVendor(target);
        }

      }
    } catch (final Exception e) {
      e.printStackTrace();
      log.error("exception occured with message : {}", e.getMessage());
    }
    session.setAttribute("isVendor", true);
    return "redirect:/";
  }


  @PostMapping(value = VENDOR_UPDATE_ACT_PATH)
  public String updateVendor(VendorDto vendor, HttpSession session) {
    log.debug("vendor dto : {}", vendor.toString());
    try {
      final String vendorSequenceId = sequenceService.generateSequence(SequenceUtil.VENDOR_ID_SEQ);
      vendorService.updateVendor(session.getAttribute("user").toString(), vendorSequenceId, vendor);
    } catch (final Exception e) {
      e.printStackTrace();
      log.error("exception occured with message : {}", e.getMessage());
    }
    return "redirect:/".concat(UiController.VENDOR_VIEW);
  }



  @PostMapping(value = BOOKING_REQUEST, produces = MediaType.TEXT_HTML_VALUE)
  public String renderBookingRequestView(Model model,
      @RequestBody MultiValueMap<String, String> formData, HttpServletRequest request,
      HttpSession session) {

    final String time = formData.getFirst("event-request-time");
    final String capacity = formData.getFirst("event-request-capacity");
    final String eventId = formData.getFirst("event-request-id");

    log.debug("time : {}, capacity : {}, event id : {}", time, capacity, eventId);

    final SimpleDateFormat pattern = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    final String fixCapacity =
        capacity != null && !capacity.equals("") && capacity.split(",").length > 0
            ? capacity.split(",")[1] : "";

    final FilterDto filter = new FilterDto();
    filter.setId(eventId);
    try {
      final ItemListDto item = listingService.findAllList(request, null, filter).get(0);
      log.debug("item : {}", item.toString());

      final User user = userService.findUserByPrincipal((String) session.getAttribute("userEmail"));

      final Calendar calendar = Calendar.getInstance();
      calendar.setTime(pattern.parse(time));
      calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(item.getRentDuration()));

      model.addAttribute("packageStartime", pattern.parse(time));
      model.addAttribute("packageEndTime", calendar.getTime());
      model.addAttribute("requester", user);

      model.addAttribute("package", item);
    } catch (final Exception exception) {
      exception.printStackTrace();
    }
    return "/contents/booking-request";
  }

  @PostMapping(value = BOOKING_PAYMENT)
  public String renderBookingPaymentView(Model model) {
    return "/contents/booking-payment";
  }

}
