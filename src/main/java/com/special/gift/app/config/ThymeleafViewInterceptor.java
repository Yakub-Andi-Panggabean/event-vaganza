package com.special.gift.app.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ThymeleafViewInterceptor extends HandlerInterceptorAdapter {

  private static final Logger log = LoggerFactory.getLogger(ThymeleafViewInterceptor.class);

  private static final String DEFAULT_LAYOUT = "layouts/index";
  private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {


    if (modelAndView == null || !modelAndView.hasView()) {
      return;
    }
    final String originalViewName = modelAndView.getViewName();

    log.debug("view name : {}", originalViewName);

    modelAndView.setViewName(DEFAULT_LAYOUT);
    modelAndView.addObject(DEFAULT_VIEW_ATTRIBUTE_NAME, originalViewName);
  }
}
