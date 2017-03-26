package com.special.gift.app.dto;

import java.io.Serializable;

public class CustomLocationDto implements Serializable {


  private static final long serialVersionUID = 1713964912258648450L;
  private String transactionId;
  private String customLocation;

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getCustomLocation() {
    return customLocation;
  }

  public void setCustomLocation(String customLocation) {
    this.customLocation = customLocation;
  }

  @Override
  public String toString() {
    return "CustomLocationDto [transactionId=" + transactionId + ", customLocation="
        + customLocation + "]";
  }



}
