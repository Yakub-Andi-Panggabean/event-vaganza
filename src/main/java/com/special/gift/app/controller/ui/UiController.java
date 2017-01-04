package com.special.gift.app.controller.ui;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value = {"menus"})
@RequestMapping(value = UiController.PATH)
public class UiController {

  // private static final Logger log = LoggerFactory.getLogger(UiController.class);

  public static final String PATH = "/";
  public static final String REGISTER = PATH + "register";
  public static final String HELP = PATH + "help";
  public static final String SEARCH = PATH + "search";

  @RequestMapping(method = RequestMethod.GET)
  public String renderIndex(Model model, HttpSession session) {
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
