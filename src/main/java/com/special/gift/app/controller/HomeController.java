package com.special.gift.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = HomeController.PATH)
public class HomeController {

  public static final String PATH = "/";
  public static final String HOME = PATH + "register";

  @RequestMapping(method = RequestMethod.GET)
  public String renderIndex(ModelAndView modelAndView) {
    modelAndView.addObject("attr", "mama");
    modelAndView.setViewName("layouts/index");
    return "fragments/main";
  }

  @RequestMapping(value = HOME, method = RequestMethod.GET)
  public String renderHomePage() {
    return "/contents/register";
  }

}
