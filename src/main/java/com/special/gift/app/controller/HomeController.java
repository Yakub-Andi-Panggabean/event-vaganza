package com.special.gift.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.special.gift.app.domain.ApplicationMenu;
import com.special.gift.app.service.ApplicationMenuService;

@Controller
@SessionAttributes("menus")
@RequestMapping(value = HomeController.PATH)
public class HomeController {

  private static final Logger log = LoggerFactory.getLogger(HomeController.class);

  public static final String PATH = "/";
  public static final String REGISTER = PATH + "register";
  public static final String HELP = PATH + "help";
  public static final String LOGIN = PATH + "login";
  public static final String SEARCH = PATH + "search";

  @Autowired
  private ApplicationMenuService applicationMenuService;

  @RequestMapping(method = RequestMethod.GET)
  public String renderIndex(Model model) {
    final List<ApplicationMenu> menus = applicationMenuService.fetchApplicationMenu();
    final List<ApplicationMenu> parentMenus = new ArrayList<>();
    for (final ApplicationMenu menu : menus) {
      if (menu.getChildren() == null || menu.getChildren().size() == 0)
        parentMenus.add(menu);
    }
    log.debug(menus.toString());
    model.addAttribute("menus", parentMenus);
    return "fragments/main";
  }

  @RequestMapping(value = REGISTER, method = RequestMethod.GET)
  public String renderRegisterPage(Model model) {
    return "/contents/register";
  }

  @RequestMapping(value = HELP, method = RequestMethod.GET)
  public String renderHelpPage(Model model) {
    return "/contents/help";
  }

  @RequestMapping(value = LOGIN, method = RequestMethod.GET)
  public String renderLoginPage(Model model) {
    return "/contents/login";
  }

  @RequestMapping(value = SEARCH, method = RequestMethod.POST)
  public String renderSearchPage(Model model) {
    return "/contents/search";
  }

}
