package com.special.gift.app.dto;

import java.io.Serializable;

public class VendorDto implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -1308673470018690675L;
  private String vendorId;
  private String vendorType;
  private String name;
  private String desc;
  private String address;
  private String handphone;
  private String phone;
  private String pic;
  private String email;
  private String user;
  private String venueVendor;

  public VendorDto() {
    super();
    // TODO Auto-generated constructor stub
  }

  public VendorDto(String vendorId, String vendorType, String name, String desc, String address,
      String handphone, String phone, String pic, String email, String user, String venueVendor) {
    super();
    this.vendorId = vendorId;
    this.vendorType = vendorType;
    this.name = name;
    this.desc = desc;
    this.address = address;
    this.handphone = handphone;
    this.phone = phone;
    this.pic = pic;
    this.email = email;
    this.user = user;
    this.venueVendor = venueVendor;
  }



  public String getVendorId() {
    return vendorId;
  }



  public void setVendorId(String vendorId) {
    this.vendorId = vendorId;
  }



  public String getVendorType() {
    return vendorType;
  }



  public void setVendorType(String vendorType) {
    this.vendorType = vendorType;
  }



  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getVenueVendor() {
    return venueVendor;
  }

  public void setVenueVendor(String venueVendor) {
    this.venueVendor = venueVendor;
  }

  @Override
  public String toString() {
    return "VendorDto [vendorId=" + vendorId + ", vendorType=" + vendorType + ", name=" + name
        + ", desc=" + desc + ", address=" + address + ", handphone=" + handphone + ", phone="
        + phone + ", pic=" + pic + ", email=" + email + ", user=" + user + ", venueVendor="
        + venueVendor + "]";
  }



}
