package com.special.gift.app.controller.ui;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
@SessionAttributes(value = {"menus"})
@RequestMapping(value = UiController.PATH)
public class UiController {

  private static final Logger log = LoggerFactory.getLogger(UiController.class);

  public static final String PATH = "/";
  public static final String REGISTER = PATH + "register";
  public static final String HELP = PATH + "help";
  public static final String SEARCH = PATH + "search";

  @Autowired
  private ApplicationMenuService applicationMenuService;

  @RequestMapping(method = RequestMethod.GET)
  public String renderIndex(Model model, HttpSession session) {
    final List<ApplicationMenu> menus = applicationMenuService.fetchApplicationMenu();
    final List<ApplicationMenu> parentMenus = new ArrayList<>();
    for (final ApplicationMenu menu : menus) {
      if (menu.getChildren() == null || menu.getChildren().size() == 0)
        parentMenus.add(menu);
    }

    model.addAttribute("menus", parentMenus);
    model.addAttribute("user", session.getAttribute("user"));
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

  @RequestMapping(value = SEARCH, method = RequestMethod.GET)
  public String renderSearchPage(Model model) {
    return "/contents/search";
  }

  // @RequestMapping(value = SEARCH, method = RequestMethod.GET)
  // public String renderErrorPage(Model model) {
  // return "/contents/search";
  // }

}
