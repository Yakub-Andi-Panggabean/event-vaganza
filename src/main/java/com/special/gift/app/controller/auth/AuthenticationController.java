package com.special.gift.app.controller.auth;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.special.gift.app.response.AuthenticationResponse;

@Controller
@RequestMapping(path = AuthenticationController.PATH)
public class AuthenticationController {

  private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

  public static final String PATH = "/";
  public static final String LOGIN = PATH + "login";
  public static final String LOGOUT = PATH + "logout";


  @RequestMapping(value = LOGIN, method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public AuthenticationResponse renderLoginPage(ServletRequest request, HttpSession httpSession) {
    log.debug("role from filter : {}", request.getAttribute("role"));
    final AuthenticationResponse authResponse = new AuthenticationResponse("/",
        (String) request.getAttribute("user"), "200", "authenticated");
    httpSession.setAttribute("user", authResponse.getUsername());
    httpSession.setAttribute("userEmail", request.getAttribute("userEmail"));
    return authResponse;
  }

  @RequestMapping(value = LOGOUT, method = RequestMethod.POST)
  @ResponseBody
  public AuthenticationResponse redirectToLogout(HttpSession httpSession,
      HttpServletResponse response, HttpServletRequest request) {
    httpSession.removeAttribute("user");
    final AuthenticationResponse authResponse = new AuthenticationResponse("/",
        (String) request.getAttribute("user"), "200", "authenticated");
    return authResponse;
  }



}
