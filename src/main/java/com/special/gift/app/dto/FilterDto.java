package com.special.gift.app.dto;

import java.io.Serializable;

public class FilterDto implements Serializable {

  private static final long serialVersionUID = -5784151640004523843L;

  private String id;
  private String keyword;
  private Integer minPrice;
  private Integer maxPrice;
  private Sorting sorting;
  private Integer capacity;
  private String city;
  private String packageType;

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public Integer getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(Integer minPrice) {
    this.minPrice = minPrice;
  }

  public Integer getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(Integer maxPrice) {
    this.maxPrice = maxPrice;
  }

  public Sorting getSorting() {
    return sorting;
  }

  public void setSorting(Sorting sorting) {
    this.sorting = sorting;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPackageType() {
    return packageType;
  }

  public void setPackageType(String packageType) {
    this.packageType = packageType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "FilterDto [id=" + id + ", keyword=" + keyword + ", minPrice=" + minPrice + ", maxPrice="
        + maxPrice + ", sorting=" + sorting + ", capacity=" + capacity + ", city=" + city
        + ", packageType=" + packageType + "]";
  }



}
