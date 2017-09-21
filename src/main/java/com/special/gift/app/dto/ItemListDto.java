package com.special.gift.app.dto;

public class ItemListDto {

  private String id;
  private String name;
  private String packageType;
  private String vendorId;
  private String category;
  private String image;
  private String url;
  private Integer price;
  private Integer capacity;
  private String location;
  private Integer discountRate;
  private Integer minimumPayment;
  private Integer paxPrice;
  private String rentDuration;
  private String description;
  private String room;
  private String vendorStyle;
  private String unit;

  public ItemListDto() {
    super();
  }

  public ItemListDto(String id, String name, String packageType, String category, String image,
      String url, Integer price, Integer capacity, String location, Integer discountRate,
      Integer minimumPayment, Integer paxPrice, String rentDuration, String description,
      String room, String vendorStyle, String vendorId, String unit) {
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
    this.discountRate = discountRate;
    this.minimumPayment = minimumPayment;
    this.paxPrice = paxPrice;
    this.rentDuration = rentDuration;
    this.description = description;
    this.room = room;
    this.vendorStyle = vendorStyle;
    this.vendorId = vendorId;
    unit = unit;
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

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
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

  public Integer getPaxPrice() {
    return paxPrice;
  }

  public void setPaxPrice(Integer paxPrice) {
    this.paxPrice = paxPrice;
  }

  public String getRentDuration() {
    return rentDuration;
  }

  public void setRentDuration(String rentDuration) {
    this.rentDuration = rentDuration;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  public String getVendorStyle() {
    return vendorStyle;
  }

  public void setVendorStyle(String vendorStyle) {
    this.vendorStyle = vendorStyle;
  }

  public String getVendorId() {
    return vendorId;
  }

  public void setVendorId(String vendorId) {
    this.vendorId = vendorId;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  @Override
  public String toString() {
    return "ItemListDto [id=" + id + ", name=" + name + ", packageType=" + packageType
        + ", vendorId=" + vendorId + ", category=" + category + ", image=" + image + ", url=" + url
        + ", price=" + price + ", capacity=" + capacity + ", location=" + location
        + ", discountRate=" + discountRate + ", minimumPayment=" + minimumPayment + ", paxPrice="
        + paxPrice + ", rentDuration=" + rentDuration + ", description=" + description + ", room="
        + room + ", vendorStyle=" + vendorStyle + ", unit=" + unit + "]";
  }



}
