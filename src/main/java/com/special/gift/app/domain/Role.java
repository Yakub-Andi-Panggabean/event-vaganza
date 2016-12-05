package com.special.gift.app.domain;

import java.util.Date;

public class Role {


  private String id;
  private String roleName;
  private boolean active;
  private Date createdDate;
  private String createdBy;
  private Date updatedDate;
  private String updatedBy;

  public Role() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Role(String id, String roleName, boolean active, Date createdDate, String createdBy,
      Date updatedDate, String updatedBy) {
    super();
    this.id = id;
    this.roleName = roleName;
    this.active = active;
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

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
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
