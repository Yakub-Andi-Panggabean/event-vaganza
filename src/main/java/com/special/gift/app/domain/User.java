package com.special.gift.app.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = User.TABLE_NAME)
public class User {

  public static final String TABLE_NAME = "user_web";

  @Id
  @Column(name = "user_id", length = 10, nullable = false)
  private String userId;

  @Column(name = "user_password", nullable = false, length = 64)
  private String password;

  @Column(name = "user_name", nullable = false, length = 50, unique = true)
  private String username;

  @Column(name = "user_zip", nullable = false, length = 10)
  private String userZip;

  @Column(name = "user_address", nullable = false, length = 100)
  private String userAddress;

  @Column(name = "user_hp", nullable = false, length = 15)
  private String handphone;

  @Column(name = "user_telp", length = 15)
  private String phone;

  @Column(name = "user_email", nullable = false, unique = true)
  private String email;

  @Column(name = "user_status", nullable = false)
  private char status;

  @OneToMany(targetEntity = Vendor.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
      mappedBy = "user")
  // @JoinColumns({@JoinColumn(name = "vendor_id"), @JoinColumn(name = "vendor_type")})
  private List<Vendor> vendor;

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



}
