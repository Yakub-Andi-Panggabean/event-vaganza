package com.special.gift.app.controller.ui;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mashape.unirest.http.JsonNode;
import com.special.gift.app.domain.PackageVenue;
import com.special.gift.app.domain.User;
import com.special.gift.app.domain.Vendor;
import com.special.gift.app.domain.VendorDesc;
import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;
import com.special.gift.app.dto.UserDto;
import com.special.gift.app.dto.VendorDto;
import com.special.gift.app.service.ListingService;
import com.special.gift.app.service.UserService;
import com.special.gift.app.service.VendorDescService;
import com.special.gift.app.service.VendorService;
import com.special.gift.app.service.VenueService;
import com.special.gift.app.service.WizardService;
import com.special.gift.app.util.CommonUtil;
import com.special.gift.app.util.HttpUtil;

@Controller
@SessionAttributes(value = {"menus"})
@RequestMapping(value = UiController.PATH)
public class UiController {

  private static final Logger log = LoggerFactory.getLogger(UiController.class);

  public static final String PATH = "/";
  public static final String HELP = "help";
  public static final String NOTIFICATION = "notification";

  // vendor
  public static final String VENDOR_REGISTER = "vendor-registration";
  public static final String VENDOR_UPDATE = "vendor-update";
  public static final String VENDOR_VIEW = "vendor-view";

  // user
  public static final String REGISTER = "register";
  public static final String USER_UPDATE = "user-update";
  public static final String USER_VIEW = "user-view";



  // package
  public static final String PACKAGE_DETAIL = "/packages/{type}/{packageId}";
  public static final String ITEM_OPTION = "/items/car";


  public static final String QUICK_BOOKING = "quick-booking";
  public static final String PLAN_EVENT_CATEGORIES = "event-categories";
  public static final String PLAN_FORWARDER = "plan-forwarder/{venue_category}";
  public static final String PLAN_MY_EVENT = "plan-event";
  public static final String BOOKING_HISTORY = "booking-history";

  public static final String ACTIVATE_USER_PATH = "registration/confirmation/{token}";
  public static final String FORGOT_PASSWORD_PATH = "/password-reset";
  public static final String RESET_PASSWORD_PATH = "/password/reset/{token}";

  // used for category > child > grandchild
  public static final String CATEGORIES = "categories";
  public static final String DETAIL = "categories/{criteria}";
  public static final String DETAIL_CHILD = "categories/{criteria}/{child}";



  @Inject
  private UserService userService;

  @Inject
  private VendorDescService vendorDescService;

  @Inject
  private ListingService listingService;

  @Inject
  private VendorService vendorService;

  @Inject
  private VenueService venueService;

  @Inject
  private WizardService wizardService;

  @Value("${image.path.location}")
  private String imagePath;


  /**
   * mapping for home page
   *
   * @param model
   * @param session
   * @return
   */
  @RequestMapping(method = RequestMethod.GET)
  public String renderIndex(Model model, HttpSession session) {
    log.debug("main page visited");

    model.addAttribute("isLogin", session.getAttribute("user") != null);

    return "/fragments/main";
  }

  /**
   *
   * mapping for register page
   *
   * @param model
   * @return
   */
  @RequestMapping(value = REGISTER, method = RequestMethod.GET)
  public String renderRegisterPage(Model model) {
    model.addAttribute("user", new UserDto());
    return "/contents/register";
  }

  @RequestMapping(value = USER_UPDATE, method = RequestMethod.GET)
  public String renderUserUpdatePage(Model model, HttpSession session) {

    try {
      final User user = userService.findUserByPrincipal((String) session.getAttribute("userEmail"));
      final UserDto dto = new UserDto();
      user.setVendor(null);
      BeanUtils.copyProperties(user, dto);

      log.debug("user dto : {}", dto.toString());

      model.addAttribute("userData", new UserDto());
      model.addAttribute("existingData", dto);
    } catch (final Exception e) {
      return "redirect:/";
    }


    return "/contents/user-update";
  }



  /**
   *
   * mapping for help page
   *
   * @param model
   * @return
   */
  @RequestMapping(value = HELP, method = RequestMethod.GET)
  public String renderHelpPage(Model model) {
    return "/outer/help";
  }


  /**
   *
   * mapping for notification page
   *
   * @param model
   * @return
   */
  @RequestMapping(value = NOTIFICATION, method = RequestMethod.GET)
  public String renderNotificationPage(Model model) {
    log.debug("fetch flash attribute : {}", model.asMap().get("existKey"));
    log.debug("fetch flash attribute : {}", model.asMap().get("existValue"));

    if (model.asMap().get("existKey") == null || model.asMap().get("existValue") == null) {
      // return "redirect:/";
    }

    return "/outer/notification";
  }


  @RequestMapping(value = BOOKING_HISTORY, method = RequestMethod.GET)
  public String renderBookingHistoryPage(HttpSession session, Model model) {

    final com.mashape.unirest.http.JsonNode json = HttpUtil.findTransactionHistory(
        session.getAttribute("accessToken").toString(), session.getAttribute("userId").toString());

    final Locale locale = new Locale("in", "ID");

    final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    final SimpleDateFormat displayFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", locale);
    final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    try {

      final List<Map<String, String>> contents = new ArrayList<>();

      final JSONArray jsonArr = new JSONArray(json.getObject().get("data").toString());

      log.info("history detail :{}",
          HttpUtil.findHistoryDetail(session.getAttribute("accessToken").toString(),
              session.getAttribute("userId").toString(), "3000000576"));

      for (int i = 0; i < jsonArr.length(); i++) {

        final Map<String, String> result = new HashMap<>();


        final JsonNode node = new JsonNode(jsonArr.get(i).toString());

        final Date date = format.parse(node.getObject().get("transaction_date").toString());
        final Date bookingDate = format.parse(node.getObject().get("date_booking").toString());


        result.put("transactionId", node.getObject().get("transaction_id").toString());
        result.put("transactionDate", displayFormat.format(date));
        result.put("bookingDate", displayFormat.format(bookingDate));

        if (node.getObject().get("methode_payment").toString().equals("t")) {
          result.put("methodPayment", "transfer");
        } else {
          result.put("methodPayment", "tunai");
        }

        if (node.getObject().get("status_payment").toString().equals("0")) {
          result.put("statusPayment", "10% Done");
        } else {
          result.put("methodPayment", "100% Done");
        }

        final Long amount = Long.parseLong(node.getObject().get("price_all").toString());

        result.put("totalAmount", currencyFormatter.format(amount));

        contents.add(result);

      }


      log.debug("data : {}", contents.toString());
      model.addAttribute("contents", contents);

    } catch (final Exception ex) {
      ex.printStackTrace();
      return "error";
    }



    return "/contents/booking-history";
  }


  @RequestMapping(value = VENDOR_REGISTER, method = RequestMethod.GET)
  public String renderVendorRegistrationPage(Model model, HttpSession session) {
    if (session.getAttribute("user") == null) {
      return "redirect:/";
    } else {
      model.addAttribute("vendor", new VendorDto());
      return "/contents/vendor-registration";
    }
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = VENDOR_UPDATE, method = RequestMethod.GET)
  public String renderVendorUpdatePage(Model model, HttpSession session) {
    try {


      final List<VendorDesc> vendorTypes = vendorDescService.findAll().getContent();
      final List<VendorDesc> availableList = new ArrayList<>();
      final List<VendorDesc> pickedList = (List<VendorDesc>) session.getAttribute("vendorTypes");


      availableList.addAll(vendorTypes);

      for (int i = 0; i < availableList.size(); i++) {
        for (int x = 0; x < pickedList.size(); x++) {
          if (pickedList.get(x).getVendorType().toLowerCase()
              .equals(availableList.get(i).getVendorType().toLowerCase())) {
            availableList.remove(i);
          }
        }
      }


      final List<VendorDesc> parents = new ArrayList<>();
      for (final VendorDesc v : availableList) {
        if (v.getVendorType().length() > 2
            && String.valueOf(v.getVendorType().charAt(2)).equals("0"))
          parents.add(v);
      }


      log.debug("picked types : {}", pickedList.toString());
      // log.debug("available types : {}", availableList.toString());

      final VendorDto vendor = (VendorDto) session.getAttribute("vendorData");

      final PackageVenue venue = venueService.findVenue(vendor.getVenueVendor());

      if (venue != null) {
        log.debug("venue data : {}", venue.toString());
        model.addAttribute("venueData", venue);
      } else {
        model.addAttribute("venueData", null);
      }

      model.addAttribute("vendor", new VendorDto());
      model.addAttribute("vendorData", vendor);
      model.addAttribute("vendorTypes", session.getAttribute("vendorTypes"));
      model.addAttribute("availableVendorTypes", parents);


    } catch (final Exception ex) {
      return "redirect:/";
    }

    return "/contents/vendor-update";
  }

  @RequestMapping(value = PACKAGE_DETAIL, method = RequestMethod.GET)
  public String renderPackageDetailPage(Model model, @PathVariable(value = "type") String type,
      @PathVariable(value = "packageId") String id, HttpServletRequest request) {

    final FilterDto filter = new FilterDto();
    filter.setId(id);
    try {
      final ItemListDto item = listingService.findAllList(request, null, filter).get(0);
      log.debug("item from controller : {}", item.toString());
      model.addAttribute("packageDetail", item);
      model.addAttribute("package_type", Character.toUpperCase(type.charAt(0)) + type.substring(1));
      model.addAttribute("packageId", id);
      model.addAttribute("imageRoot", imagePath);
      model.addAttribute("isRentable", Integer.parseInt(item.getRentDuration()) > 0);
      model.addAttribute("isCountable", item.getCapacity() > 0);

    } catch (final Exception e) {
      e.printStackTrace();
    }

    return "/contents/package-detail";
  }


  @RequestMapping(value = USER_VIEW, method = RequestMethod.GET)
  public String renderUserViewPage(Model model, HttpSession session) {
    try {
      final User user = userService.findUserByPrincipal((String) session.getAttribute("userEmail"));
      final UserDto dto = new UserDto();
      user.setVendor(null);
      BeanUtils.copyProperties(user, dto);
      model.addAttribute("existingData", dto);

    } catch (final Exception e) {
      return "redirect:/";
    }
    return "/contents/user-data-view";
  }

  @RequestMapping(value = VENDOR_VIEW, method = RequestMethod.GET)
  public String renderVendorViewPage(Model model, HttpSession session) {
    try {

      if (session.getAttribute("user") == null) {
        return "redirect:/";
      }
      model.addAttribute("user", session.getAttribute("user"));
      final User user = userService.findUserByPrincipal((String) session.getAttribute("userEmail"));
      final List<Vendor> data = vendorService.findByUser(user);
      final List<VendorDesc> vendorTypes = new ArrayList<>();

      final VendorDto vendorDto = new VendorDto();

      BeanUtils.copyProperties(data.get(0), vendorDto);

      log.debug("vendor dto : {}", vendorDto.toString());

      for (final Vendor vendor : data) {
        vendorTypes.add(vendorDescService.findById(vendor.getVendorId().getType()));
      }


      log.debug("vendor types : {}", vendorTypes.toString());

      model.addAttribute("existingVendor", vendorDto);
      model.addAttribute("vendorTypes", vendorTypes);
      model.addAttribute("vendorConfirmations",
          vendorService.getVendorConfirmations(data.get(0).getVendorId().getVendorId()));

      session.setAttribute("vendorData", vendorDto);
      session.setAttribute("vendorTypes", vendorTypes);

    } catch (final Exception e) {
      e.printStackTrace();
      return "redirect:/";
    }
    return "/contents/vendor-data-view";
  }


  @RequestMapping(value = QUICK_BOOKING, method = RequestMethod.GET)
  public String renderQuickBookingPage(Model model) {


    model.addAttribute("categories", vendorDescService.findAllParents());

    model.addAttribute("imageRoot", imagePath);

    return "fragments/quick-booking";
  }


  @RequestMapping(value = PLAN_MY_EVENT, method = RequestMethod.GET)
  public String renderPlanPage(Model model, HttpSession session, HttpServletRequest request) {

    if (session.getAttribute("user") == null) {

      return "redirect:/";

    } else {

      try {

        final User user =
            userService.findUserByPrincipal((String) session.getAttribute("userEmail"));

        // final String venueCategories = (String) model.asMap().get("venueCategory");
        final String venueCategories = (String) session.getAttribute("venueCategory");

        model.addAttribute("imageRoot", imagePath);
        model.addAttribute("steps", wizardService.findWizardSteps());
        model.addAttribute("userId", user.getUserId());

        if (venueCategories != null) {
          model.addAttribute("venueCategory", venueCategories);
        }

        return "contents/plan-event";


      } catch (final Exception exception) {
        return "redirect:/";
      }


    }
  }


  @RequestMapping(value = PLAN_EVENT_CATEGORIES, method = RequestMethod.GET)
  public String renderEventCategories() {

    return "contents/plan-event-categories";
  }

  @RequestMapping(value = PLAN_FORWARDER, method = RequestMethod.GET)
  public String eventForwarder(RedirectAttributes attributes,
      @PathVariable("venue_category") String venueCategories, HttpSession session) {


    attributes.addFlashAttribute("venueCategory", venueCategories);
    session.setAttribute("venueCategory", venueCategories);

    return "redirect:/plan-event";
  }


  @GetMapping(value = ACTIVATE_USER_PATH)
  public String activateUser(Model model, @PathVariable(value = "token") String token) {

    try {
      final User user = userService.activateUser(token);
      model.addAttribute("userName", user.getUsername());
    } catch (final Exception ex) {
      log.info("activating user error : {}", ex.getMessage());
      return "error";
    }

    return "outer/activated_notification";
  }

  @GetMapping(value = FORGOT_PASSWORD_PATH)
  public String forgotPassword() {
    return "contents/forgot-password";
  }

  @GetMapping(value = RESET_PASSWORD_PATH)
  public String resetPassword(Model model, @PathVariable(value = "token") String token) {
    try {

      if (userService.checkUserByToken(token)) {
        model.addAttribute("token", token);
        return "contents/reset_password";
      }

    } catch (final Exception ex) {
      ex.printStackTrace();
      log.info("activating user error : {}", ex.getMessage());
    }
    return "error";
  }



  /**
   *
   * mapping for root category page
   *
   * @param model
   * @param session
   * @return
   */
  @RequestMapping(value = CATEGORIES, method = RequestMethod.GET)
  public String renderCategoriesPage(Model model) {
    return "/contents/parent-categories";
  }

  /**
   *
   * mapping for detail category page
   *
   * @param model
   * @param criteria
   * @param id
   * @param session
   * @return
   */
  @RequestMapping(value = DETAIL, method = RequestMethod.GET)
  public String renderDetailPage(Model model, @PathVariable(value = "criteria") String name,
      @RequestParam(value = "c") String id,
      @RequestParam(value = "start", defaultValue = "0", required = false) int start,
      @RequestParam(value = "limit", defaultValue = "12", required = false) int limit,
      HttpServletRequest request) {


    try {

      if (id == null || name == null || id.isEmpty() || name.isEmpty()) {
        return "redirect:/";
      } else {

        final VendorDesc vendorDesc = vendorDescService.findById(id);

        log.debug("parent_from detail : {}", name);



        if (vendorDesc == null) {
          return "redirect:/";
        } else {

          final List<ItemListDto> categoryList =
              listingService.findAllList(request, vendorDesc.getVendorType());

          // used for url
          model.addAttribute("parent", name.replace(" ", "-"));

          // used for banner
          model.addAttribute("category_name", name.replace("-", " "));
          model.addAttribute("category_description", vendorDesc.getVendorDescription());
          model.addAttribute("children",
              vendorDescService.findAllChildren(vendorDesc.getVendorType()));

          model.addAttribute("itemList",
              categoryList.subList(
                  categoryList.size() > start ? start
                      : categoryList.size() > 0 ? categoryList.size() - 1 : 0,
                  categoryList.size() > limit ? limit : categoryList.size()));

          model.addAttribute("totalPage",
              new Double(Math.ceil((double) categoryList.size() / limit)).intValue());
          model.addAttribute("totalDisplayItem", start > limit ? start - limit : limit);
          model.addAttribute("pagingNumber", CommonUtil.PAGING_NUMBER);
          model.addAttribute("imagePath", imagePath);

          return "/contents/finder";
        }
      }

    } catch (final Exception ex) {
      ex.printStackTrace();
      return "redirect:/";
    }
  }


  /**
   *
   * mapping for second level detail category page
   *
   * @param model
   * @param criteria
   * @param child
   * @param id
   * @param session
   * @return
   */
  @RequestMapping(value = DETAIL_CHILD, method = RequestMethod.GET)
  public String renderDetailChildPage(Model model, @PathVariable(value = "criteria") String name,
      @PathVariable(value = "child") String child, @RequestParam(value = "c") String id,
      @RequestParam(value = "start", defaultValue = "0", required = false) int start,
      @RequestParam(value = "limit", defaultValue = "12", required = false) int limit,
      HttpServletRequest request) {

    try {

      if (id == null || name == null || id.isEmpty() || name.isEmpty()) {
        return "redirect:/";
      } else {


        final VendorDesc parent = vendorDescService.findById(id.substring(0, 2).concat("0"));

        if (parent == null) {
          log.debug("parent is null");
          return "redirect:/";
        } else {

          final VendorDesc vendorDesc = vendorDescService.findById(id);

          if (vendorDesc == null) {
            return "redirect:/".concat("categories/")
                .concat(parent.getVendorTypeName().replace(" ", "-").toLowerCase()).concat("?")
                .concat("c=").concat(parent.getVendorType());
          } else {

            final List<ItemListDto> categoryList =
                listingService.findAllList(request, vendorDesc.getVendorType());

            // for request param
            model.addAttribute("root_type", parent.getVendorType());

            // child name banner
            log.debug("child name : {}", child.replace("-", " "));
            model.addAttribute("child_name", child.replace("-", " "));

            model.addAttribute("child_description", vendorDesc.getVendorDescription());
            // parent for parent url
            model.addAttribute("parent",
                parent.getVendorTypeName().replace(" ", "-").toLowerCase());
            // parent for parent banner
            model.addAttribute("parentName", parent.getVendorTypeName());
            model.addAttribute("children",
                vendorDescService.findAllChildren(vendorDesc.getVendorType()));


            model.addAttribute("itemList",
                categoryList.subList(
                    categoryList.size() > start ? start
                        : categoryList.size() > 0 ? categoryList.size() - 1 : 0,
                    categoryList.size() > limit ? limit : categoryList.size()));

            model.addAttribute("totalPage",
                new Double(Math.ceil(categoryList.size() / (double) limit)).intValue());
            model.addAttribute("totalDisplayItem", start > limit ? start - limit : limit);
            model.addAttribute("pagingNumber", CommonUtil.PAGING_NUMBER);
            model.addAttribute("imagePath", imagePath);


            return "/contents/finder";
          }


        }
      }
    } catch (final Exception ex) {
      ex.printStackTrace();
      return "redirect:/";
    }
  }



}
