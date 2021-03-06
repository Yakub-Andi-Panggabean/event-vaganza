package com.special.gift.app.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = PackageVendor.TABLE_NAME)
public class PackageVendor {

  public static final String TABLE_NAME = "package_vendor";

  @Id
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

  @Column(name = "discount_rate", nullable = true)
  private Integer discountRate;

  @Column(name = "minimum_payment", nullable = false)
  private int minimumPayment;

  @Column(name = "time_package", nullable = false, length = 20)
  private String timePackage;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
  @JoinColumns({@JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id"), @JoinColumn(
      name = "vendor_type", referencedColumnName = "vendor_type", columnDefinition = "CHAR(2)")})
  private Vendor vendor;

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

  public Integer getDiscountRate() {
    return discountRate;
  }

  public void setDiscountRate(Integer discountRate) {
    this.discountRate = discountRate;
  }

  public int getMinimumPayment() {
    return minimumPayment;
  }

  public void setMinimumPayment(int minimumPayment) {
    this.minimumPayment = minimumPayment;
  }

  public String getTimePackage() {
    return timePackage;
  }

  public void setTimePackage(String timePackage) {
    this.timePackage = timePackage;
  }

  public Vendor getVendor() {
    return vendor;
  }

  public void setVendor(Vendor vendor) {
    this.vendor = vendor;
  }

}
