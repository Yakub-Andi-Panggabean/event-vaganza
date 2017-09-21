package com.special.gift.app.controller.act;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.special.gift.app.controller.ui.UiController;
import com.special.gift.app.domain.PackageVenue;
import com.special.gift.app.domain.User;
import com.special.gift.app.domain.Vendor;
import com.special.gift.app.domain.VendorId;
import com.special.gift.app.domain.WizardStepMaster;
import com.special.gift.app.domain.WizardValue;
import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;
import com.special.gift.app.dto.PlanEventDto;
import com.special.gift.app.dto.UserDto;
import com.special.gift.app.dto.VendorDto;
import com.special.gift.app.service.BookingTransactionService;
import com.special.gift.app.service.ListingService;
import com.special.gift.app.service.SequenceService;
import com.special.gift.app.service.UserService;
import com.special.gift.app.service.VendorService;
import com.special.gift.app.service.VenueService;
import com.special.gift.app.service.WizardService;
import com.special.gift.app.util.CommonUtil;
import com.special.gift.app.util.HttpUtil;
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
  private static final String VENDOR_CONFIRMATION = "vendor-confirmation";

  // booking
  public static final String BOOKING_REQUEST = "booking/request";
  public static final String BOOKING_PAYMENT = "booking/payment";
  public static final String BOOKING_TRANSACTION = "booking/transaction";


  public static final String PLAN_PACKAGE_PATH = "plan-package";
  public static final String PLAN_PACKAGE_PAYMENT = "plan/booking/payment";
  public static final String PLAN_BOOKING_TRANSACTION = "plan/booking/transaction";

  public static final String RESET_PASSWORD = "/password/reset";


  @Inject
  private UserService userService;

  @Inject
  private VendorService vendorService;

  @Inject
  private VenueService venueService;

  @Inject
  private SequenceService sequenceService;

  @Inject
  private ListingService listingService;

  @Inject
  private BookingTransactionService bookingService;

  @Inject
  private WizardService wizardService;

  @Inject
  private SequenceService sequence;

  @Value("${image.path.location}")
  private String imagePath;

  @Value("${account.name1}")
  private String accountName1;

  @Value("${account.name2}")
  private String accountName2;

  @Value("${account.rek1}")
  private String norek1;

  @Value("${account.rek2}")
  private String norek2;

  final SimpleDateFormat pattern = new SimpleDateFormat("yyyy/MM/dd HH:mm");
  private static final String SEPARATOR = ":";


  @PostMapping(value = USER_ACT_PATH)
  public String addNewuser(@ModelAttribute UserDto user, RedirectAttributes redirectAttributes) {
    log.debug("user dto : {}", user.toString());
    try {
      final User userData = new User();
      BeanUtils.copyProperties(user, userData);
      userData.setUserId(sequenceService.generateSequence(SequenceUtil.USER_ID_SEQ));
      userService.insertUser(userData);
      redirectAttributes.addFlashAttribute("existKey", "email");
      redirectAttributes.addFlashAttribute("existValue", userData.getEmail());
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
    return "redirect:/notification";
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
    final String qty = formData.getFirst("event-request-qty");

    log.debug("time : {}, capacity : {}, event id : {}", time, capacity, eventId);

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
      model.addAttribute("imageRoot", imagePath);
      model.addAttribute("qty", Integer.parseInt(qty));

      model.addAttribute("package", item);
    } catch (final Exception exception) {
      exception.printStackTrace();
    }
    return "/contents/booking-request";
  }

  @PostMapping(value = BOOKING_PAYMENT)
  public String renderBookingPaymentView(Model model,
      @RequestBody MultiValueMap<String, String> formData, HttpServletRequest request,
      HttpSession session) {

    final String packageId = formData.getFirst("package_id");
    final String eventDate = formData.getFirst("event_date");
    final String eventQty = formData.getFirst("package_qty");

    log.debug("package id : {}, event date : {}", packageId, eventDate);

    final SimpleDateFormat pattern = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    try {

      final FilterDto filter = new FilterDto();
      filter.setId(packageId);

      final ItemListDto item = listingService.findAllList(request, null, filter).get(0);

      final User user = userService.findUserByPrincipal((String) session.getAttribute("userEmail"));

      final Calendar calendar = Calendar.getInstance();
      calendar.setTime(pattern.parse(eventDate));
      calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(item.getRentDuration()));

      final Calendar calendarMaxPayment = Calendar.getInstance();
      calendarMaxPayment.setTime(pattern.parse(eventDate));
      calendarMaxPayment.add(Calendar.DATE, -30);

      model.addAttribute("packageStartime", pattern.parse(eventDate));
      model.addAttribute("packageEndTime", calendar.getTime());
      model.addAttribute("requester", user);
      model.addAttribute("package", item);
      model.addAttribute("imageRoot", imagePath);
      model.addAttribute("maxPaymentDate", calendarMaxPayment.getTime());
      model.addAttribute("acc1", accountName1);
      model.addAttribute("acc2", accountName2);
      model.addAttribute("norek1", norek1);
      model.addAttribute("norek2", norek2);
      model.addAttribute("qty", Integer.parseInt(eventQty));

    } catch (final Exception ex) {
      ex.printStackTrace();
    }

    return "/contents/booking-payment";
  }

  @PostMapping(value = BOOKING_TRANSACTION)
  public String bookingTransaction(Model model, @RequestBody MultiValueMap<String, String> formData,
      HttpServletRequest request, HttpSession session) {

    final SimpleDateFormat pattern = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    try {
      final String packageId = formData.getFirst("package_id");
      final String bookingDate = formData.getFirst("booking_date");
      final char method = formData.getFirst("payment_method").charAt(0);
      final String amount = formData.getFirst("payment_amount");
      final String bookingQty = formData.getFirst("payment_qty");

      final FilterDto filter = new FilterDto();
      filter.setId(packageId);

      log.debug("package id : {}, booking date : {}, methode : {}", packageId, bookingDate, method);

      final ItemListDto item = listingService.findAllList(request, null, filter).get(0);

      final Date bookingTime = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(bookingDate);

      /*
       * final User user = userService.findUserByPrincipal((String)
       * session.getAttribute("userEmail"));
       *
       * final BookingTransactionDto dto = new BookingTransactionDto();
       *
       *
       *
       *
       * final Vendor vendor = vendorService.findById(new VendorId(item.getVendorId(),
       * item.getCategory()));
       *
       * dto.setDateBooking(new SimpleDateFormat("yyyyMMdd").format(bookingTime));
       * dto.setTimeBooking(new SimpleDateFormat("HH:mm").format(bookingTime));
       * dto.setEventId(packageId); dto.setMethodPayment(method); dto.setPriceAll(item.getPrice());
       * dto.setPricePayment(item.getMinimumPayment()); dto.setStatuspayment('0');
       * dto.setStatusTransaction('0'); dto.setTransactionDate(new
       * SimpleDateFormat("yyyyMMdd").format(new Date())); dto.setTransactionTime(new
       * SimpleDateFormat("HH:mm").format(new Date())); dto.setUser(user); dto.setVendor(vendor !=
       * null ? vendor : vendorService.findBySingleId(item.getVendorId()));
       * dto.setTransactionId(sequence.generateSequence(SequenceUtil.TRANSACTION_ID_SEQ));
       * dto.setVenueAddress(user.getUserAddress());
       * dto.setGroupTransactionId(sequence.generateSequence(SequenceUtil.GROUP_TRANSACTION_ID_SEQ))
       * ;
       *
       * final BookingTransaction transaction = new BookingTransaction();
       *
       * BeanUtils.copyProperties(dto, transaction);
       *
       * log.debug("vendor type : {},id :{}", item.getVendorId(), item.getCategory());
       * log.debug("transaction  {}", transaction.toString());
       *
       * bookingService.saveBookingTransaction(transaction);
       *
       */

      final ObjectNode json = JsonNodeFactory.instance.objectNode();

      if (item.getPackageType().equals(CommonUtil.PACKAGE_VENDOR)) {
        json.put("package_id", packageId);
        json.put("venue_id", "");
      } else {
        json.put("package_id", "");
        json.put("venue_id", packageId);
      }

      json.put("vendor_type", item.getCategory());
      json.put("date_booking", new SimpleDateFormat("yyyyMMdd").format(bookingTime));
      json.put("time_booking", new SimpleDateFormat("HHmm").format(bookingTime));
      json.put("methode_payment", method + "".toUpperCase());
      json.put("methode_payment", method + "".toUpperCase());
      json.put("quantity", Integer.parseInt(bookingQty));

      if (amount.equals("fee")) {
        json.put("status_payment", "0");
      } else {
        json.put("status_payment", "1");
      }


      final com.mashape.unirest.http.JsonNode response =
          HttpUtil.createSingleBookingRequest(session.getAttribute("accessToken").toString(),
              session.getAttribute("userId").toString(), json.toString());

      if (response.getObject().get("data").toString().equals("null")) {
        log.error("invalid response accepted from api : {}", json.toString());
        return "error";
      } else {
        log.info("response : {}", response.getObject());
      }



    } catch (final Exception exception) {
      exception.printStackTrace();
    }

    return "outer/help";
  }


  @PostMapping(value = PLAN_PACKAGE_PATH)
  public String planPackage(Model model, @RequestBody MultiValueMap<String, String> formData,
      HttpServletRequest request, HttpServletResponse response) {
    try {

      final String date = formData.getFirst("wizard-event-date");
      final String venue = formData.getFirst("wizard-event-venue");
      final String catering = formData.getFirst("wizard-event-catering");
      final String decoration = formData.getFirst("wizard-event-decoration");
      final String makeup = formData.getFirst("wizard-event-makeup");
      final String photo = formData.getFirst("wizard-event-photo");
      final String eo = formData.getFirst("wizard-event-eo");
      final String others = formData.getFirst("wizard-event-others");
      final String transport = formData.getFirst("wizard-event-transport");
      final String location = formData.getFirst("wizard-event-location");

      final PlanEventDto dto = new PlanEventDto();

      dto.setDate(date);
      dto.setDecoration(decoration);
      dto.setEo(eo);
      dto.setMakeUp(makeup);
      dto.setOthers(others);
      dto.setPhotography(photo);
      dto.setTransport(transport);
      dto.setVenue(venue);
      dto.setCatering(catering);
      if (location == null) {
        dto.setLocation("");
      } else {
        dto.setLocation(location);
      }

      final List<String> list = new ArrayList<>();
      final List<ItemListDto> result = new ArrayList<>();


      if (catering != null && !catering.equals("")) {
        list.add(catering);
      }
      if (decoration != null && !decoration.equals("")) {
        list.add(decoration);
      }
      if (makeup != null && !makeup.equals("")) {
        list.add(makeup);
      }
      if (photo != null && !photo.equals("")) {
        list.add(photo);
      }
      if (eo != null && !eo.equals("")) {
        list.add(eo);
      }
      if (others != null && !others.equals("")) {
        list.add(others);
      }
      if (transport != null && !transport.equals("")) {
        list.add(transport);
      }

      final List<ItemListDto> contents = new ArrayList<>();

      log.debug(
          " date : {}, venue : {}, catering: {},decoration : {}, makeup : {}, photo : {}, eo: {}, others : {}, transport : {}, location : {}",
          date, venue, catering, decoration, makeup, photo, eo, others, transport, location);



      FilterDto filter;
      if (list.size() > 0) {
        for (final String key : list) {

          filter = new FilterDto();
          filter.setId(key);

          final List<ItemListDto> items = listingService.findAllList(request, null, filter);

          if (items.size() > 0) {
            result.add(items.get(0));
          }
        }

        contents.addAll(result);
      }


      if (venue != null && !venue.equals("")) {

        final PackageVenue packageVenue = venueService.findByVendorId(venue);

        if (packageVenue != null) {
          final ItemListDto item = new ItemListDto();
          item.setCapacity(Integer.parseInt(packageVenue.getRoomCapacity()));
          item.setCategory(packageVenue.getVendorDesc().getVendorType());
          item.setDescription(packageVenue.getVenuePackage());
          item.setDiscountRate(packageVenue.getDiscountRate());
          item.setId(packageVenue.getVenueId());
          item.setImage(packageVenue.getVenuePortofolio());
          item.setLocation(new StringBuilder(packageVenue.getVenueAddress()).append(",")
              .append(packageVenue.getCity()).toString());
          item.setMinimumPayment(packageVenue.getMinimumPayment());
          item.setName(packageVenue.getVenueName());
          item.setPackageType("venue");
          item.setPaxPrice(packageVenue.getPaxPrice());
          item.setPrice(packageVenue.getRentalPrice());
          item.setRentDuration(packageVenue.getTimeRent());
          item.setRoom(packageVenue.getVenueRoom());
          item.setUrl(new StringBuilder(request.getContextPath()).append("/packages/")
              .append("venue").append("/").append(packageVenue.getVenueId()).toString());
          item.setVendorId(packageVenue.getVendor());
          item.setVendorStyle("");
          contents.add(item);
        }
      }



      // log.debug("res : {}", result.toString());

      model.addAttribute("customPackage", dto);
      model.addAttribute("items", contents);
      model.addAttribute("eventDate", date);
      model.addAttribute("imageRoot", imagePath);
      model.addAttribute("customLocation", dto.getLocation());


    } catch (final Exception exception) {
      exception.printStackTrace();
    }
    return "/contents/custom-package";
  }


  @PostMapping(value = PLAN_PACKAGE_PAYMENT)
  public String planPayment(Model model, @RequestBody MultiValueMap<String, String> formData,
      HttpServletRequest request, HttpSession session) {

    FilterDto filterDto = null;
    final Map<String, Integer> itemList = new HashMap<>();

    final SimpleDateFormat pattern = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    final Calendar calendar = Calendar.getInstance();


    try {

      final String transactionId = formData.getFirst("plan_event_transaction_id");
      final String packageQty = formData.getFirst("plan_event_transaction_quantity");

      log.debug("transactionid : {}", transactionId);

      final User user = userService.findUserByPrincipal((String) session.getAttribute("userEmail"));

      final WizardValue wizardValue = wizardService.findWizardValue(transactionId);

      final String stepValues[] = wizardValue.getContent().split(",");
      final String stepQty[] = packageQty.split(",");

      int totalPrice = 0;
      int totalMinimumPayment = 0;
      ItemListDto choosenVenue = null;
      String date = "";
      final StringBuilder listOfPackages = new StringBuilder();
      String eventLocation = "";
      int multiplier = 1;

      for (int i = 0; i < stepValues.length; i++) {

        final WizardStepMaster stepMaster =
            wizardService.findStepById(Integer.parseInt(stepValues[i].split("=")[0]));



        if (i == 1) {
          eventLocation = stepValues[i].split("=")[1];
        }

        if (!stepMaster.getPackageCategory().equals("---")) {


          filterDto = new FilterDto();
          filterDto.setId(stepValues[i].split("=")[1]);
          final List<ItemListDto> dtos = listingService.findAllList(request, null, filterDto);
          if (!dtos.isEmpty()) {

            if (stepMaster.getPackageCategory().equals("000")) {
              choosenVenue = dtos.get(0);

              itemList.put(stepMaster.getStepName(), dtos.get(0).getPrice());
              totalPrice = totalPrice + (dtos.get(0).getPrice());
              totalMinimumPayment = totalMinimumPayment + (dtos.get(0).getMinimumPayment());

            } else {



              for (final String qty : stepQty) {

                log.info("quantity : {}", qty);

                log.info("quantity : {}, step value : {}", qty.split(":")[0],
                    stepValues[i].split("=")[1]);

                if (qty.split(":")[0].equals(stepValues[i].split("=")[1])) {
                  multiplier = Integer.parseInt(qty.split(":")[1]);
                }


              }

              itemList.put(stepMaster.getStepName().concat(" x " + multiplier),
                  dtos.get(0).getPrice() * multiplier);
              totalPrice = totalPrice + (dtos.get(0).getPrice() * multiplier);
              totalMinimumPayment =
                  totalMinimumPayment + (dtos.get(0).getMinimumPayment() * multiplier);

              listOfPackages.append(dtos.get(0).getId()).append("-").append(multiplier).append(":");

              multiplier = 1;

            }



          }
        } else {
          date = stepValues[i].split("=")[1];
        }

      }



      final Calendar calendarMaxPayment = Calendar.getInstance();
      calendarMaxPayment.setTime(pattern.parse(date));
      calendarMaxPayment.add(Calendar.DATE, -30);

      calendar.setTime(pattern.parse(date));
      if (choosenVenue != null) {
        calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(choosenVenue.getRentDuration()));
      }


      log.debug("items : {}", itemList.toString());
      log.debug("items : {}", listOfPackages.toString());


      model.addAttribute("listOfPackages",
          listOfPackages.toString() != null && !listOfPackages.toString().isEmpty()
              ? listOfPackages.subSequence(0, listOfPackages.length() - 1).toString() : "");
      model.addAttribute("eventDate", calendar.getTime());
      model.addAttribute("eventLocation", eventLocation);
      model.addAttribute("imageRoot", imagePath);
      model.addAttribute("packageStartime", pattern.parse(date));
      model.addAttribute("packageEndTime", calendar.getTime());
      model.addAttribute("maxPaymentDate", calendarMaxPayment.getTime());
      model.addAttribute("venue", choosenVenue);
      model.addAttribute("items", itemList);
      model.addAttribute("user", user);
      model.addAttribute("totalPrice", totalPrice);
      model.addAttribute("totalMinimumPayment", totalMinimumPayment);
      model.addAttribute("acc1", accountName1);
      model.addAttribute("acc2", accountName2);
      model.addAttribute("norek1", norek1);
      model.addAttribute("norek2", norek2);

    } catch (final Exception exception) {
      exception.printStackTrace();
    }

    return "contents/plan-payment";
  }

  @SuppressWarnings("deprecation")
  @PostMapping(value = PLAN_BOOKING_TRANSACTION)
  public String renderPlanBookingPage(Model model,
      @RequestBody MultiValueMap<String, String> formData, HttpServletRequest request,
      HttpSession session) {

    try {

      final String packages = formData.getFirst("planned_packages_id");
      final String venue = formData.getFirst("planned_venue");
      final String eventDate = formData.getFirst("planned_event_date");
      final char method = formData.getFirst("plan_payment_method").charAt(0);
      final String amount = formData.getFirst("plan_payment_amount");
      final String eventLocation = formData.getFirst("plan_event_location");

      log.info("packages : {}, venue : {}, date : {}", packages, venue, eventDate);


      if (packages != null) {

        final Date bookingTime = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(eventDate);



        final String[] packagesArr = packages.split(":");

        /*
         * final FilterDto filter = new FilterDto();
         *
         * final User user = userService.findUserByPrincipal((String)
         * session.getAttribute("userEmail"));
         *
         * final List<BookingTransaction> bookingTransactions = new ArrayList<>();
         *
         *
         * final String groupTransaction =
         * sequenceService.generateSequence(SequenceUtil.GROUP_TRANSACTION_ID_SEQ);
         *
         * final FilterDto venueFilter = new FilterDto(); venueFilter.setId(venue); final
         * ItemListDto venueEntity = listingService.findAllList(request, null, venueFilter).get(0);
         *
         *
         *
         * // log.debug("group transaction id ---> {}", groupTransaction);
         *
         * log.info("amount :{}, package length:{}", amount, packagesArr.length);
         *
         *
         * for (int i = 0; i < packagesArr.length; i++) {
         *
         * log.info("package array : {}", packagesArr[i]);
         *
         * if (groupTransaction != null && !packagesArr[i].isEmpty()) {
         *
         *
         * final String sequenceId = sequence.generateSequence(SequenceUtil.TRANSACTION_ID_SEQ);
         *
         *
         * filter.setId(packagesArr[i]);
         *
         * log.debug("package id : {}, booking date : {}, methode : {}", packagesArr[i], eventDate,
         * method);
         *
         * final ItemListDto item = listingService.findAllList(request, null, filter).get(0);
         *
         * // log.debug("item to string : {}", item.toString()); final BookingTransactionDto dto =
         * new BookingTransactionDto(); dto.setDateBooking(new
         * SimpleDateFormat("yyyyMMdd").format(bookingTime)); dto.setTimeBooking(new
         * SimpleDateFormat("HH:mm").format(bookingTime)); dto.setEventId(packagesArr[i]);
         * dto.setMethodPayment(method); dto.setPriceAll(item.getPrice());
         *
         * if (amount.equals("fee")) {
         *
         * dto.setPricePayment(item.getMinimumPayment());
         *
         * } else {
         *
         * dto.setPricePayment(item.getPrice());
         *
         * }
         *
         * dto.setStatuspayment('0'); dto.setStatusTransaction('0'); dto.setTransactionDate(new
         * SimpleDateFormat("yyyyMMdd").format(new Date())); dto.setTransactionTime(new
         * SimpleDateFormat("HH:mm").format(new Date())); dto.setUser(user);
         * dto.setVendor(vendorService.findBySingleId(item.getVendorId()));
         * dto.setTransactionId(sequenceId); dto.setGroupTransactionId(groupTransaction);
         *
         * if (eventLocation != null && !eventLocation.equals("")) {
         * dto.setVenueAddress(eventLocation); } else {
         * dto.setVenueAddress(venueEntity.getLocation()); }
         *
         * final BookingTransaction transaction = new BookingTransaction();
         *
         * BeanUtils.copyProperties(dto, transaction);
         *
         * bookingTransactions.add(transaction);
         *
         *
         * }
         *
         * }
         *
         *
         * final Iterable<BookingTransaction> iterable = bookingTransactions;
         *
         *
         * // log.debug("iterable : {}", iterable.toString());
         *
         * bookingService.saveBookingBatch(iterable);
         */

        final ObjectMapper mapper = new ObjectMapper();

        log.info("event location : {}", eventLocation);

        final ObjectNode json = JsonNodeFactory.instance.objectNode();
        json.put("date_booking", new SimpleDateFormat("yyyyMMdd").format(bookingTime));
        json.put("time_booking", new SimpleDateFormat("HHmm").format(bookingTime));
        json.put("methode_payment", method + "".toUpperCase());
        if (amount.equals("fee")) {
          json.put("status_payment", "0");
        } else {
          json.put("status_payment", "1");
        }

        if (venue.matches("[0-9]+")) {
          final FilterDto venueFilter = new FilterDto();
          venueFilter.setId(venue);
          final ItemListDto venueEntity =
              listingService.findAllList(request, null, venueFilter).get(0);
          json.put("venue_address", "");
          json.put("venue_id", venue);
        } else {
          json.put("venue_address", eventLocation);
          json.put("venue_id", "");
        }

        final ArrayNode jsonArrQty = json.putArray("package_quantities");
        final ArrayNode jsonArrPackageId = json.putArray("package_ids");

        for (int i = 0; i < packagesArr.length; i++) {

          if (!packagesArr[i].equals("")) {

            jsonArrPackageId.add(packagesArr[i].split("-")[0]);
            jsonArrQty.add(packagesArr[i].split("-")[1]);

          }

        }



        log.info("json request : {}:", json.toString());



        final com.mashape.unirest.http.JsonNode response =
            HttpUtil.createGroupBookingRequest(session.getAttribute("accessToken").toString(),
                session.getAttribute("userId").toString(), json.toString());

        log.info("json response : {}", response.getObject().toString());

        if (response.getObject().get("data").toString().equals("null")) {
          return "error";
        }


      }



    } catch (final Exception exception) {
      exception.printStackTrace();
    }

    return "outer/help";
  }


  @PostMapping(value = VENDOR_CONFIRMATION)
  public String vendorConfirmation(Model model, @RequestBody MultiValueMap<String, String> formData,
      HttpServletRequest request) {

    log.debug("form data to string : {}", formData.toString());

    final List<String> confirmedTransaction = new ArrayList<>();

    try {
      final Set<String> keys = formData.keySet();

      for (final String key : keys) {
        log.debug("keys : {}", key);

        if (formData.getFirst(key).equals("a")) {
          confirmedTransaction.add(key);
        }
      }


      if (confirmedTransaction.size() > 0) {
        for (final String confirmed : confirmedTransaction) {
          vendorService.confirmBooking(confirmed);
        }
      }


    } catch (final Exception exception) {
      exception.printStackTrace();
    }
    return "redirect:/vendor-view";
  }


  @PostMapping(value = RESET_PASSWORD)
  public String passwordReset(@RequestBody MultiValueMap<String, String> formData) {


    try {


      final String newPassword = formData.getFirst("forgot_password_new");
      final String newPasswordConfirmation = formData.getFirst("forgot_password_new_confirm");
      final String token = formData.getFirst("forgot_password_token");

      if (!newPassword.equals(newPasswordConfirmation)) {
        throw new RuntimeException("password confirmation is different");
      }


      userService.resetPassword(token, newPassword);

      return "outer/password_reset_success";
    } catch (final Exception exception) {
      log.debug("exception occured with message : {}", exception.getMessage());
      return "error";
    }


  }


}
