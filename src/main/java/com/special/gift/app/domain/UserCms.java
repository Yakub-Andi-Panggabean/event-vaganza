package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = UserCms.TABLE_NAME)
public class UserCms {

  public static final String TABLE_NAME = "user_cms";

  @Column(name = "user_id", nullable = false, length = 10)
  private String userId;
  @Column(name = "user_password", nullable = false, length = 64)
  private String password;
  @Column(name = "user_name", nullable = false, length = 30)
  private String username;
  @Column(name = "name_user", nullable = false, length = 30)
  private String fullname;
  @Column(name = "phone_number", nullable = false, length = 15)
  private String phone;
  @Column(name = "user_email", nullable = false, length = 15)
  private String email;
  @Column(name = "user_status", nullable = false)
  private char status;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public char getStatus() {
    return status;
  }

  public void setStatus(char status) {
    this.status = status;
  }



}
