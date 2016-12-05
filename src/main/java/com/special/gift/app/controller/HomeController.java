package com.special.gift.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.special.gift.app.domain.ApplicationMenu;
import com.special.gift.app.service.ApplicationMenuService;

@Controller
@RequestMapping(value = HomeController.PATH)
public class HomeController {

  public static final String PATH = "/";
  public static final String HOME = PATH + "register";

  @Autowired
  private ApplicationMenuService applicationMenuService;

  @RequestMapping(method = RequestMethod.GET)
  public String renderIndex(ModelAndView modelAndView) {
    final List<ApplicationMenu> menus = applicationMenuService.fetchApplicationMenu();
    modelAndView.addObject("menus", menus);
    return "fragments/main";
  }

  @RequestMapping(value = HOME, method = RequestMethod.GET)
  public String renderHomePage() {
    return "/contents/register";
  }

}
