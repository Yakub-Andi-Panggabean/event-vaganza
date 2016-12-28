package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = PackageVendor.TABLE_NAME)
public class PackageVendor {

  public static final String TABLE_NAME = "package_vendor";

  @Column(name = "vendor_id", nullable = false, length = 10)
  private String vendorId;
  @Column(name = "package_id", nullable = false, length = 10)
  private String packageId;
  @Column(name = "package_name", nullable = false, length = 10)
  private String packageName;
  @Column(name = "package_capacity", nullable = false)
  private int packageCapacity;
  @Column(name = "package_desc", nullable = false, length = 300)
  private String packageDesc;
  @Column(name = "package_category", nullable = false, length = 20)
  private String packageCategory;
  @Column(name = "package_style", nullable = false, length = 20)
  private String packageStyle;
  @Column(name = "package_price", nullable = false)
  private int packagePrice;
  @Column(name = "package_img", nullable = false, length = 100)
  private String packageImg;
  @Column(name = "package_promo", nullable = false, length = 100)
  private String packagePromo;
  @Column(name = "discount_rate", nullable = false)
  private int discountRate;
  @Column(name = "vendor_type", nullable = false, length = 3)
  private String vendorType;
  @Column(name = "minimum_payment", nullable = false)
  private int minimumPayment;
  @Column(name = "time_package", nullable = false)
  private int timePackage;

  public String getVendorId() {
    return vendorId;
  }

  public void setVendorId(String vendorId) {
    this.vendorId = vendorId;
  }

  public String getPackageId() {
    return packageId;
  }

  public void setPackageId(String packageId) {
    this.packageId = packageId;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public int getPackageCapacity() {
    return packageCapacity;
  }

  public void setPackageCapacity(int packageCapacity) {
    this.packageCapacity = packageCapacity;
  }

  public String getPackageDesc() {
    return packageDesc;
  }

  public void setPackageDesc(String packageDesc) {
    this.packageDesc = packageDesc;
  }

  public String getPackageCategory() {
    return packageCategory;
  }

  public void setPackageCategory(String packageCategory) {
    this.packageCategory = packageCategory;
  }

  public String getPackageStyle() {
    return packageStyle;
  }

  public void setPackageStyle(String packageStyle) {
    this.packageStyle = packageStyle;
  }

  public int getPackagePrice() {
    return packagePrice;
  }

  public void setPackagePrice(int packagePrice) {
    this.packagePrice = packagePrice;
  }

  public String getPackageImg() {
    return packageImg;
  }

  public void setPackageImg(String packageImg) {
    this.packageImg = packageImg;
  }

  public String getPackagePromo() {
    return packagePromo;
  }

  public void setPackagePromo(String packagePromo) {
    this.packagePromo = packagePromo;
  }

  public int getDiscountRate() {
    return discountRate;
  }

  public void setDiscountRate(int discountRate) {
    this.discountRate = discountRate;
  }

  public String getVendorType() {
    return vendorType;
  }

  public void setVendorType(String vendorType) {
    this.vendorType = vendorType;
  }

  public int getMinimumPayment() {
    return minimumPayment;
  }

  public void setMinimumPayment(int minimumPayment) {
    this.minimumPayment = minimumPayment;
  }

  public int getTimePackage() {
    return timePackage;
  }

  public void setTimePackage(int timePackage) {
    this.timePackage = timePackage;
  }



}