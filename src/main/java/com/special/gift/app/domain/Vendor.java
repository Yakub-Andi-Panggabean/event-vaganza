package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = Vendor.TABLE_NAME)
public class Vendor {

  public static final String TABLE_NAME = "vendor";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid")
  @Column(name = "vendor_id", columnDefinition = "CHAR(32)")
  private String id;

  @Column(name = "vendor_type", nullable = false, length = 2)
  private String type;

  @Column(name = "user_id", nullable = false, length = 10)
  private String userId;

  @Column(name = "vendor_name", nullable = false, length = 50)
  private String name;

  @Column(name = "vendor_desc", nullable = false, length = 100)
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

}
