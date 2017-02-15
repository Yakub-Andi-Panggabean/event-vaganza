package com.special.gift.app.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = Vendor.TABLE_NAME)
public class Vendor {

  public static final String TABLE_NAME = "vendor";

  @EmbeddedId
  private VendorId vendorId;

  @Column(name = "vendor_name", nullable = false, length = 50)
  private String name;

  @Column(name = "vendor_desc", nullable = false, columnDefinition = "longtext")
  private String desc;

  @Column(name = "vendor_address", nullable = false, length = 100)
  private String address;

  @Column(name = "vendor_hp", nullable = false, length = 100)
  private String handphone;

  @Column(name = "vendor_telp", nullable = false, length = 100)
  private String phone;

  @Column(name = "vendor_pic", nullable = false, length = 100)
  private String pic;

  @Column(name = "vendor_email", nullable = false, length = 100)
  private String email;

  @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = true)
  private User user;

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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public VendorId getVendorId() {
    return vendorId;
  }

  public void setVendorId(VendorId vendorId) {
    this.vendorId = vendorId;
  }

}
