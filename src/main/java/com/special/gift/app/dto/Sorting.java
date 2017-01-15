package com.special.gift.app.dto;

public class Sorting {

  public String propertyName;
  private String order;

  public Sorting() {
    super();
  }

  public Sorting(String propertyName, String order) {
    super();
    this.propertyName = propertyName;
    this.order = order;
  }

  public String getPropertyName() {
    return propertyName;
  }

  public void setPropertyName(String propertyName) {
    this.propertyName = propertyName;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  @Override
  public String toString() {
    return "Sorting [propertyName=" + propertyName + ", order=" + order + "]";
  }


}
