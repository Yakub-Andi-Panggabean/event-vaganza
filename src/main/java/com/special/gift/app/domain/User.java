package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = User.TABLE_NAME)
public class User {

  public static final String TABLE_NAME = "user";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "user_id", columnDefinition = "CHAR(32)")
  private String id;

  @Column(name = "user_password", nullable = false, length = 64)
  private String password;

  @Column(name = "vendor_id", nullable = false, length = 10)
  private String vendorId;

  @Column(name = "user_name", nullable = false, length = 10, unique = true)
  private String username;

  @Column(name = "user_zip", nullable = false, length = 10, unique = true)
  private String userZip;

  @Column(name = "user_address", nullable = false, length = 100, unique = true)
  private String userAddress;

  @Column(name = "user_hp", nullable = false, length = 15)
  private String handphone;

  @Column(name = "user_telp", nullable = false, length = 15)
  private String phone;

  @Column(name = "user_email", nullable = false, unique = true)
  private String email;

  @Column(name = "user_status", nullable = false, unique = true)
  private char status;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getVendorId() {
    return vendorId;
  }

  public void setVendorId(String vendorId) {
    this.vendorId = vendorId;
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



}
