package com.special.gift.app.controller.ui;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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

  // used for category > child > grandchild
  public static final String CATEGORIES = "categories";
  public static final String DETAIL = "categories/{criteria}";
  public static final String DETAIL_CHILD = "categories/{criteria}/{child}";

  // package
  public static final String PACKAGE_DETAIL = "/packages/{type}/{packageId}";
  public static final String ITEM_OPTION = "/items/car";


  public static final String QUICK_BOOKING = "quick-booking";
  public static final String PLAN_MY_EVENT = "plan-event";


  @Inject
  private UserService userService;

  @Inject
  private VendorDescService vendorDescService;

  @Inject
  private ListingService listingService;

  @Inject
  private VendorService vendorService;

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

    return "fragments/main";
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
      @RequestParam(value = "c") String id) {

    if (id == null || name == null || id.isEmpty() || name.isEmpty()) {
      return "redirect:/";
    } else {

      final VendorDesc vendorDesc = vendorDescService.findById(id);

      log.debug("parent_from detail : {}", name);

      if (vendorDesc == null) {
        return "redirect:/";
      } else {

        // used for url
        model.addAttribute("parent", name.replace(" ", "-"));

        // used for banner
        model.addAttribute("category_name", name.replace("-", " "));
        model.addAttribute("category_description", vendorDesc.getVendorDescription());

        model.addAttribute("children1",
            vendorDescService.findAllChildren(vendorDesc.getVendorType(), 0, 6));
        model.addAttribute("children2",
            vendorDescService.findAllChildren(vendorDesc.getVendorType(), 6, 13));
        model.addAttribute("children3",
            vendorDescService.findAllChildren(vendorDesc.getVendorType(), 13, 19));

        log.debug("vendor desc : {}", vendorDesc.toString());

        return "/contents/finder";
      }
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
      @PathVariable(value = "child") String child, @RequestParam(value = "c") String id) {

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

          // for request param
          model.addAttribute("root_type", parent.getVendorType());

          // child name banner
          log.debug("child name : {}", child.replace("-", " "));
          model.addAttribute("child_name", child.replace("-", " "));

          model.addAttribute("child_description", vendorDesc.getVendorDescription());

          // parent for parent url
          model.addAttribute("parent", parent.getVendorTypeName().replace(" ", "-").toLowerCase());

          // parent for parent banner
          model.addAttribute("parentName", parent.getVendorTypeName());


          model.addAttribute("children1",
              vendorDescService.findAllChildren(vendorDesc.getVendorType(), 0, 6));
          model.addAttribute("children2",
              vendorDescService.findAllChildren(vendorDesc.getVendorType(), 6, 13));
          model.addAttribute("children3",
              vendorDescService.findAllChildren(vendorDesc.getVendorType(), 13, 19));

          log.debug("vendor desc : {}", vendorDesc.toString());

          return "/contents/finder";
        }


      }
    }
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
      return "redirect:/";
    }

    return "/outer/notification";
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


      log.debug("picked types : {}", pickedList.toString());
      log.debug("available types : {}", availableList.toString());


      model.addAttribute("vendor", new VendorDto());
      model.addAttribute("vendorData", session.getAttribute("vendorData"));
      model.addAttribute("vendorTypes", session.getAttribute("vendorTypes"));
      model.addAttribute("availableVendorTypes", availableList);

    } catch (final Exception ex) {
      ex.printStackTrace();
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


    model.addAttribute("categories1", vendorDescService.findAllParents(0, 4));
    model.addAttribute("categories2", vendorDescService.findAllParents(4, 8));
    model.addAttribute("categories3", vendorDescService.findAllParents(8, 12));

    model.addAttribute("imageRoot", imagePath);

    return "fragments/quick-booking";
  }


  @RequestMapping(value = PLAN_MY_EVENT, method = RequestMethod.GET)
  public String renderPlanPage(Model model) {

    model.addAttribute("categories1", vendorDescService.findAllParents(0, 4));
    model.addAttribute("categories2", vendorDescService.findAllParents(4, 8));
    model.addAttribute("categories3", vendorDescService.findAllParents(8, 12));

    model.addAttribute("imageRoot", imagePath);

    return "contents/plan-event";
  }

  // unused
  // @RequestMapping(value = ITEM_OPTION, method = RequestMethod.GET)
  // public String renderItemOptionViewPage(Model model) {
  // return "/contents/item-option";
  // }



}
