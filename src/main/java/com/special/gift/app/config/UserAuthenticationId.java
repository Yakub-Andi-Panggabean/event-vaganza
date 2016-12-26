package com.special.gift.app.config;

import org.springframework.social.UserIdSource;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationId implements UserIdSource {

  @Override
  public String getUserId() {
    // TODO Auto-generated method stub
    return null;
  }

}
