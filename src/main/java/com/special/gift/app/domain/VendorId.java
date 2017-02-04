package com.special.gift.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VendorId implements Serializable {

  private static final long serialVersionUID = 8236547036759825097L;

  @Column(name = "vendor_id", nullable = false, length = 10)
  private String vendorId;

  @Column(name = "vendor_type", columnDefinition = "CHAR(2)", nullable = false)
  private String type;


  public VendorId() {
    super();
    // TODO Auto-generated constructor stub
  }

  public VendorId(String vendorId, String type) {
    super();
    this.vendorId = vendorId;
    this.type = type;
  }

  public String getVendorId() {
    return vendorId;
  }

  public void setVendorId(String vendorId) {
    this.vendorId = vendorId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    result = prime * result + ((vendorId == null) ? 0 : vendorId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final VendorId other = (VendorId) obj;
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
      return false;
    if (vendorId == null) {
      if (other.vendorId != null)
        return false;
    } else if (!vendorId.equals(other.vendorId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "VendorId [vendorId=" + vendorId + ", type=" + type + "]";
  }



}
