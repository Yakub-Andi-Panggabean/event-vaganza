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

  @RequestMapping(value = DETAIL, method = RequestMethod.GET)
  public String renderDetailPage(Model model, @PathVariable(value = "criteria") String criteria,
      @RequestParam(value = "c") String id, HttpSession session) {
    model.addAttribute("user", session.getAttribute("user"));
    model.addAttribute("parent", criteria.replace("-", " "));

    final VendorDesc vendorDesc = vendorDescService.findById(id);

    if (id == null || criteria == null || vendorDesc == null) {
      return "redirect:/";
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


  @RequestMapping(value = DETAIL_CHILD, method = RequestMethod.GET)
  public String renderDetailChildPage(Model model,
      @PathVariable(value = "criteria") String criteria,
      @PathVariable(value = "child") String child, @RequestParam(value = "c") String id,
      HttpSession session) {
    final VendorDesc parent = vendorDescService.findById(id.substring(0, 1).concat("0"));

    model.addAttribute("user", session.getAttribute("user"));
    model.addAttribute("root", criteria.replace("-", " "));
    model.addAttribute("root_type", parent.getVendorType());
    model.addAttribute("parent", child.replace("-", " "));
    model.addAttribute("parent_variable", parent.getVendorDescription().replace(" ", "-"));

    final VendorDesc vendorDesc = vendorDescService.findById(id);

    if (id == null || criteria == null || vendorDesc == null) {
      return "redirect:/";
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
