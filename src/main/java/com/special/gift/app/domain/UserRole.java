package com.special.gift.app.domain;

import java.util.List;

public class UserRole {

  private User user;
  private List<Role> roles;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

}
