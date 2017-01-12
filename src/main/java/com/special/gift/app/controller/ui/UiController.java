package com.special.gift.app.controller.ui;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.special.gift.app.domain.VendorDesc;
import com.special.gift.app.dto.UserDto;
import com.special.gift.app.dto.VendorDto;
import com.special.gift.app.service.VendorDescService;

@Controller
@SessionAttributes(value = {"menus"})
@RequestMapping(value = UiController.PATH)
public class UiController {

  private static final Logger log = LoggerFactory.getLogger(UiController.class);

  public static final String PATH = "/";
  public static final String REGISTER = PATH + "register";
  public static final String HELP = PATH + "help";
  public static final String SEARCH = PATH + "search";
  public static final String NOTIFICATION = PATH + "notification";
  public static final String VENDOR_REGISTER = PATH + "vendor-registration";

  // used for category > child > grandchild
  public static final String CATEGORIES = PATH + "categories";
  public static final String DETAIL = PATH + "categories/{criteria}";
  public static final String DETAIL_CHILD = PATH + "categories/{criteria}/{child}";

  @Inject
  private VendorDescService vendorDescService;

  /**
   * mapping for home page
   *
   * @param model
   * @param session
   * @return
   */
  @RequestMapping(method = RequestMethod.GET)
  public String renderIndex(Model model, HttpSession session) {
    model.addAttribute("user", session.getAttribute("user"));
    model.addAttribute("categories1", vendorDescService.findAllParents(0, 4));
    model.addAttribute("categories2", vendorDescService.findAllParents(4, 8));
    model.addAttribute("categories3", vendorDescService.findAllParents(8, 12));
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

  /**
   *
   * mapping for search page
   *
   * @param model
   * @param session
   * @return
   */
  @RequestMapping(value = SEARCH, method = RequestMethod.GET)
  public String renderSearchPage(Model model, HttpSession session) {
    model.addAttribute("user", session.getAttribute("user"));
    return "/contents/search";
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
  public String renderCategoriesPage(Model model, HttpSession session) {
    model.addAttribute("user", session.getAttribute("user"));
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
      @RequestParam(value = "c") String id, HttpSession session) {
    model.addAttribute("user", session.getAttribute("user"));

    if (id == null || name == null || id == null) {
      return "redirect:/";
    } else {

      final VendorDesc vendorDesc = vendorDescService.findById(id);

      // used for url
      model.addAttribute("parent", name.replace(" ", "-"));

      // used for banner
      model.addAttribute("category_name", name.replace("-", " "));
      model.addAttribute("category_description", vendorDesc.getVendorDescription());

      log.debug("parent_from detail : {}", name);

      if (vendorDesc == null) {
        return "redirect:/";
      } else {

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
      @PathVariable(value = "child") String child, @RequestParam(value = "c") String id,
      HttpSession session) {

    if (id == null || name == null || id == null) {
      return "redirect:/";
    } else {

      final VendorDesc parent = vendorDescService.findById(id.substring(0, 1).concat("0"));

      if (parent == null) {
        return "redirect:/";
      } else {
        final VendorDesc vendorDesc = vendorDescService.findById(id);

        model.addAttribute("user", session.getAttribute("user"));

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



        if (vendorDesc == null) {
          return "redirect:/".concat("categories/")
              .concat(parent.getVendorTypeName().replace(" ", "-").toLowerCase()).concat("?")
              .concat("c=").concat(parent.getVendorType());
        }

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
      model.addAttribute("user", session.getAttribute("user"));
      model.addAttribute("vendor", new VendorDto());
      return "/contents/vendor-registration";
    }
  }


}
