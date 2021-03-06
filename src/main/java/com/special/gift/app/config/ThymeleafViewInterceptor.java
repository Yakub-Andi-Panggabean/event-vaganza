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
  private static final String DEFAULT_HEADER_ATTRIBUTE_NAME = "header";
  private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";
  private static final String DEFAULT_FOOTER_ATTRIBUTE_NAME = "footer";

  private static final String DEFAULT_HEADER_ATTRIBUTE_VALUE = "fragments/header";
  private static final String AUTHENTICATED_HEADER_ATTRIBUTE_VALUE =
      "fragments/authenticated-header";
  private static final String OUTER_HEADER_VALUE = "fragments/outer-header";

  private static final String DEFAULT_FOOTER_ATTRIBUTE_VALUE = "fragments/footer";

  private static final String NOT_FOUND_PAGE = "unexpected/not-found";

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

    if (modelAndView == null || !modelAndView.hasView()) {
      return;
    }

    final String originalViewName = modelAndView.getViewName();
    final String principal = (String) request.getSession().getAttribute("user");

    log.debug("response code : {}", response.getStatus());

    log.debug("request attribute user : {}", request.getSession().getAttribute("user"));

    log.debug("view name : {}", originalViewName);



    if (!originalViewName.contains("redirect:")) {

      if (originalViewName.contains("/outer") || originalViewName.contains("error")) {
        // define header
        modelAndView.addObject(DEFAULT_HEADER_ATTRIBUTE_NAME, OUTER_HEADER_VALUE);

        // define footer
        modelAndView.addObject(DEFAULT_FOOTER_ATTRIBUTE_NAME, DEFAULT_FOOTER_ATTRIBUTE_VALUE);
      } else {
        // define header
        if (principal == null) {
          modelAndView.addObject(DEFAULT_HEADER_ATTRIBUTE_NAME, DEFAULT_HEADER_ATTRIBUTE_VALUE);
        } else {
          modelAndView.addObject(DEFAULT_HEADER_ATTRIBUTE_NAME,
              AUTHENTICATED_HEADER_ATTRIBUTE_VALUE);
        }

        // define footer
        modelAndView.addObject(DEFAULT_FOOTER_ATTRIBUTE_NAME, DEFAULT_FOOTER_ATTRIBUTE_VALUE);
      }

      modelAndView.setViewName(DEFAULT_LAYOUT);
      // define content
      log.debug("view name : {}", originalViewName);
      modelAndView.addObject(DEFAULT_VIEW_ATTRIBUTE_NAME, originalViewName);


    }

  }
}
