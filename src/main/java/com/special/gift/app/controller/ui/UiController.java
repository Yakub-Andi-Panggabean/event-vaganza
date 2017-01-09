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

  // used for category > child > grandchild
  public static final String CATEGORIES = PATH + "categories";
  public static final String DETAIL = PATH + "categories/{criteria}";
  public static final String DETAIL_CHILD = PATH + "categories/{criteria}/{child}";

  @Inject
  private VendorDescService vendorDescService;

  @RequestMapping(method = RequestMethod.GET)
  public String renderIndex(Model model, HttpSession session) {
    model.addAttribute("user", session.getAttribute("user"));
    model.addAttribute("categories1", vendorDescService.findAllParents(0, 4));
    model.addAttribute("categories2", vendorDescService.findAllParents(4, 8));
    model.addAttribute("categories3", vendorDescService.findAllParents(8, 12));
    return "fragments/main";
  }

  @RequestMapping(value = REGISTER, method = RequestMethod.GET)
  public String renderRegisterPage(Model model) {
    model.addAttribute("user", new UserDto());
    return "/contents/register";
  }

  @RequestMapping(value = SEARCH, method = RequestMethod.GET)
  public String renderSearchPage(Model model, HttpSession session) {
    model.addAttribute("user", session.getAttribute("user"));
    return "/contents/search";
  }

  @RequestMapping(value = CATEGORIES, method = RequestMethod.GET)
  public String renderCategoriesPage(Model model, HttpSession session) {
    model.addAttribute("user", session.getAttribute("user"));
    return "/contents/parent-categories";
  }

  @RequestMapping(value = DETAIL, method = RequestMethod.GET)
  public String renderDetailPage(Model model, @PathVariable(value = "criteria") String criteria,
      @RequestParam(value = "c") String id, HttpSession session) {
    model.addAttribute("user", session.getAttribute("user"));

    if (id == null || criteria == null || id == null) {
      return "redirect:/";
    } else {
      // used for url
      model.addAttribute("parent", criteria.replace(" ", "-"));

      // used for banner
      model.addAttribute("category_name", criteria.replace("-", " "));

      log.debug("parent_from detail : {}", criteria);

      final VendorDesc vendorDesc = vendorDescService.findById(id);

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


  @RequestMapping(value = DETAIL_CHILD, method = RequestMethod.GET)
  public String renderDetailChildPage(Model model,
      @PathVariable(value = "criteria") String criteria,
      @PathVariable(value = "child") String child, @RequestParam(value = "c") String id,
      HttpSession session) {

    if (id == null || criteria == null || id == null) {
      return "redirect:/";
    } else {

      final VendorDesc parent = vendorDescService.findById(id.substring(0, 1).concat("0"));

      if (parent == null) {
        return "redirect:/";
      } else {

        model.addAttribute("user", session.getAttribute("user"));

        // for request param
        model.addAttribute("root_type", parent.getVendorType());

        // child name banner
        log.debug("child name : {}", child.replace("-", " "));
        model.addAttribute("child_name", child.replace("-", " "));

        // parent for parent url
        model.addAttribute("parent", parent.getVendorDescription().replace(" ", "-").toLowerCase());

        // parent for parent banner
        model.addAttribute("parentName", parent.getVendorDescription());


        final VendorDesc vendorDesc = vendorDescService.findById(id);

        if (vendorDesc == null) {
          return "redirect:/".concat("categories/")
              .concat(parent.getVendorDescription().replace(" ", "-").toLowerCase()).concat("?")
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

  @RequestMapping(value = HELP, method = RequestMethod.GET)
  public String renderHelpPage(Model model) {
    return "/outer/help";
  }

  @RequestMapping(value = NOTIFICATION, method = RequestMethod.GET)
  public String renderNotificationPage(Model model) {
    log.debug("fetch flash attribute : {}", model.asMap().get("existKey"));
    log.debug("fetch flash attribute : {}", model.asMap().get("existValue"));

    if (model.asMap().get("existKey") == null || model.asMap().get("existValue") == null) {
      return "redirect:/";
    }

    return "/outer/notification";
  }



}
