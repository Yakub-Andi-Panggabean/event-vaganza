package com.special.gift.app.dto;

public class ItemListDto {

  private String id;
  private String name;
  private String packageType;
  private String category;
  private String image;
  private String url;
  private String price;
  private Integer intPrice;
  private Integer capacity;
  private String location;
  private Integer discountRate;
  private Integer minimumPayment;

  public ItemListDto() {
    super();
  }

  public ItemListDto(String id, String name, String packageType, String category, String image,
      String url, String price, int intPrice, Integer capacity, String location,
      Integer discountRate, Integer minimumPayment) {
    super();
    this.id = id;
    this.name = name;
    this.packageType = packageType;
    this.category = category;
    this.image = image;
    this.url = url;
    this.price = price;
    this.capacity = capacity;
    this.location = location;
    this.intPrice = intPrice;
    this.discountRate = discountRate;
    this.minimumPayment = minimumPayment;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getPackageType() {
    return packageType;
  }

  public void setPackageType(String packageType) {
    this.packageType = packageType;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Integer getIntPrice() {
    return intPrice;
  }

  public void setIntPrice(Integer intPrice) {
    this.intPrice = intPrice;
  }

  public Integer getDiscountRate() {
    return discountRate;
  }

  public void setDiscountRate(Integer discountRate) {
    this.discountRate = discountRate;
  }

  public Integer getMinimumPayment() {
    return minimumPayment;
  }

  public void setMinimumPayment(Integer minimumPayment) {
    this.minimumPayment = minimumPayment;
  }



}
