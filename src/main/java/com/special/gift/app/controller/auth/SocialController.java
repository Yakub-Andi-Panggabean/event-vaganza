package com.special.gift.app.controller.auth;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = SocialController.PATH)
public class SocialController {

  public static final String PATH = "/";
  public static final String FACEBOOK_AUTH = PATH + "facebook";


  private final Facebook facebook;
  private final ConnectionRepository connectionRepository;


  @Inject
  public SocialController(Facebook facebook, ConnectionRepository connectionRepository) {
    super();
    this.facebook = facebook;
    this.connectionRepository = connectionRepository;
  }


  @RequestMapping(value = FACEBOOK_AUTH, method = RequestMethod.GET)
  public String facebookAuthentication() {
    // final User user = facebook.userOperations().getUserProfile();
    return "connect/facebookConnect";
  }

}
