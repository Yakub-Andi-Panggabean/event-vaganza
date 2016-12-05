package com.special.gift.app.domain;

import java.util.List;

public class RoleMenu {

  private Role role;
  private List<Menu> menus;

  public RoleMenu() {
    super();
    // TODO Auto-generated constructor stub
  }

  public RoleMenu(Role role, List<Menu> menus) {
    super();
    this.role = role;
    this.menus = menus;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public List<Menu> getMenus() {
    return menus;
  }

  public void setMenus(List<Menu> menus) {
    this.menus = menus;
  }

  @Override
  public String toString() {
    return "RoleMenu [role=" + role + ", menus=" + menus + "]";
  }



}
