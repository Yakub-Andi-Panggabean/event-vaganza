package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = VendorDesc.TABLE_NAME)
public class VendorDesc {

  public static final String TABLE_NAME = "vendor_description";

  @Id
  @Column(name = "vendor_type", columnDefinition = "CHAR(2)", nullable = false)
  private String vendorType;

  @Column(name = "vendor_type_name", nullable = false, length = 50)
  private String vendorTypeName;

  @Column(name = "vendor_description", nullable = false, length = 100)
  private String vendorDescription;

  public String getVendorType() {
    return vendorType;
  }

  public void setVendorType(String vendorType) {
    this.vendorType = vendorType;
  }

  public String getVendorDescription() {
    return vendorDescription;
  }

  public void setVendorDescription(String vendorDescription) {
    this.vendorDescription = vendorDescription;
  }

  public String getVendorTypeName() {
    return vendorTypeName;
  }

  public void setVendorTypeName(String vendorTypeName) {
    this.vendorTypeName = vendorTypeName;
  }



}
