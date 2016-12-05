package com.special.gift.app.domain;

import java.util.Date;
import java.util.List;

public class Menu {

  private String id;
  private String name;
  private String url;
  private boolean active;
  private List<Menu> children;
  private Date createdDate;
  private String createdBy;
  private Date updatedDate;
  private String updatedBy;

  public Menu() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Menu(String id, String name, String url, boolean active, List<Menu> children,
      Date createdDate, String createdBy, Date updatedDate, String updatedBy) {
    super();
    this.id = id;
    this.name = name;
    this.url = url;
    this.active = active;
    this.children = children;
    this.createdDate = createdDate;
    this.createdBy = createdBy;
    this.updatedDate = updatedDate;
    this.updatedBy = updatedBy;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public List<Menu> getChildren() {
    return children;
  }

  public void setChildren(List<Menu> children) {
    this.children = children;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }



}
