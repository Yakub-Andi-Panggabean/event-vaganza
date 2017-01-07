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
import org.springframework.web.bind.annotation.SessionAttributes;

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
  public static final String FINDER = PATH + "find/{criteria}";

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
  public String renderSearchPage(Model model) {
    return "/contents/search";
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

  @RequestMapping(value = FINDER, method = RequestMethod.GET)
  public String renderFinderPage(Model model, @PathVariable(value = "criteria") String criteria) {
    log.debug("criteria : {}", criteria);
    return "/contents/finder";
  }


}
