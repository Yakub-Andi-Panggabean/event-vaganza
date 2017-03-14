package com.special.gift.app.dto;

import java.io.Serializable;

public class FilterDto implements Serializable {

  private static final long serialVersionUID = -5784151640004523843L;

  private String id;
  private String vendorId;
  private String keyword;
  private Integer minPrice;
  private Integer maxPrice;
  private Sorting sorting;
  private Integer capacity;
  private String city;
  private String packageType;
  private String category;
  private String parent;
  private String venue;

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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getParent() {
    return parent;
  }

  public void setParent(String parent) {
    this.parent = parent;
  }


  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public String getVendorId() {
    return vendorId;
  }

  public void setVendorId(String vendorId) {
    this.vendorId = vendorId;
  }

  @Override
  public String toString() {
    return "FilterDto [id=" + id + ", vendorId=" + vendorId + ", keyword=" + keyword + ", minPrice="
        + minPrice + ", maxPrice=" + maxPrice + ", sorting=" + sorting + ", capacity=" + capacity
        + ", city=" + city + ", packageType=" + packageType + ", category=" + category + ", parent="
        + parent + ", venue=" + venue + "]";
  }



}
