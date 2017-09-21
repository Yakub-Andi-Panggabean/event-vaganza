package com.special.gift.app.dto;

import java.io.Serializable;
import java.util.List;

import com.special.gift.app.domain.Vendor;

public class UserDto implements Serializable {

  private static final long serialVersionUID = -7186951818484201746L;
  private String userId;
  private String password;
  private String username;
  private String userZip;
  private String userAddress;
  private String handphone;
  private String phone;
  private String email;
  private char status;
  private List<Vendor> vendor;
  private String fullName;

  public UserDto() {
    super();
    // TODO Auto-generated constructor stub
  }

  public UserDto(String userId, String password, String username, String userZip,
      String userAddress, String handphone, String phone, String email, char status,
      List<Vendor> vendor, String fullName) {
    super();
    this.userId = userId;
    this.password = password;
    this.username = username;
    this.userZip = userZip;
    this.userAddress = userAddress;
    this.handphone = handphone;
    this.phone = phone;
    this.email = email;
    this.status = status;
    this.vendor = vendor;
    this.fullName = fullName;
  }



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

  public String getUserZip() {
    return userZip;
  }

  public void setUserZip(String userZip) {
    this.userZip = userZip;
  }

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }

  public String getHandphone() {
    return handphone;
  }

  public void setHandphone(String handphone) {
    this.handphone = handphone;
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

  public List<Vendor> getVendor() {
    return vendor;
  }

  public void setVendor(List<Vendor> vendor) {
    this.vendor = vendor;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Override
  public String toString() {
    return "UserDto [userId=" + userId + ", password=" + password + ", username=" + username
        + ", userZip=" + userZip + ", userAddress=" + userAddress + ", handphone=" + handphone
        + ", phone=" + phone + ", email=" + email + ", status=" + status + ", vendor=" + vendor
        + ", fullName=" + fullName + "]";
  }



}
