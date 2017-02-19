package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = PackageVenue.TABLE_NAME)
public class PackageVenue {

  public static final String TABLE_NAME = "package_venue";

  @Id
  @Column(name = "venue_id", nullable = false, length = 10)
  private String venueId;

  @Column(name = "venue_room", nullable = false, length = 50)
  private String venueRoom;

  @Column(name = "venue_name", nullable = false, length = 50)
  private String venueName;

  @Column(name = "room_capacity", nullable = false, length = 5)
  private String roomCapacity;

  @Column(name = "venue_package", nullable = false, length = 100)
  private String venuePackage;

  @Column(name = "venue_portofolio", nullable = false, length = 200)
  private String venuePortofolio;

  @Column(name = "venue_promo", nullable = false, length = 100)
  private String venuePromo;

  @Column(name = "discount_rate", nullable = false)
  private int discountRate;

  @Column(name = "rental_price", nullable = false)
  private int rentalPrice;

  @Column(name = "time_rent", nullable = false, length = 10)
  private String timeRent;

  @Column(name = "city", nullable = false, length = 20)
  private String city;

  @Column(name = "pax_price", nullable = false)
  private int paxPrice;

  @Column(name = "minimum_payment", nullable = false)
  private int minimumPayment;

  @Column(name = "vendor_id", nullable = true)
  private String vendor;

  @Column(name = "venue_address", nullable = true, length = 60)
  private String venueAddress;

  @ManyToOne
  @JoinColumn(name = "vendor_type", referencedColumnName = "vendor_type")
  private VendorDesc vendorDesc;

  public String getVenueId() {
    return venueId;
  }

  public void setVenueId(String venueId) {
    this.venueId = venueId;
  }

  public String getVenueRoom() {
    return venueRoom;
  }

  public void setVenueRoom(String venueRoom) {
    this.venueRoom = venueRoom;
  }

  public String getRoomCapacity() {
    return roomCapacity;
  }

  public void setRoomCapacity(String roomCapacity) {
    this.roomCapacity = roomCapacity;
  }

  public String getVenuePackage() {
    return venuePackage;
  }

  public void setVenuePackage(String venuePackage) {
    this.venuePackage = venuePackage;
  }

  public String getVenuePortofolio() {
    return venuePortofolio;
  }

  public void setVenuePortofolio(String venuePortofolio) {
    this.venuePortofolio = venuePortofolio;
  }

  public String getVenuePromo() {
    return venuePromo;
  }

  public void setVenuePromo(String venuePromo) {
    this.venuePromo = venuePromo;
  }

  public int getDiscountRate() {
    return discountRate;
  }

  public void setDiscountRate(int discountRate) {
    this.discountRate = discountRate;
  }

  public int getRentalPrice() {
    return rentalPrice;
  }

  public void setRentalPrice(int rentalPrice) {
    this.rentalPrice = rentalPrice;
  }

  public String getTimeRent() {
    return timeRent;
  }

  public void setTimeRent(String timeRent) {
    this.timeRent = timeRent;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public int getPaxPrice() {
    return paxPrice;
  }

  public void setPaxPrice(int paxPrice) {
    this.paxPrice = paxPrice;
  }

  public int getMinimumPayment() {
    return minimumPayment;
  }

  public void setMinimumPayment(int minimumPayment) {
    this.minimumPayment = minimumPayment;
  }

  public String getVenueName() {
    return venueName;
  }

  public void setVenueName(String venueName) {
    this.venueName = venueName;
  }

  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public String getVenueAddress() {
    return venueAddress;
  }

  public void setVenueAddress(String venueAddress) {
    this.venueAddress = venueAddress;
  }

  public VendorDesc getVendorDesc() {
    return vendorDesc;
  }

  public void setVendorDesc(VendorDesc vendorDesc) {
    this.vendorDesc = vendorDesc;
  }

  @Override
  public String toString() {
    return "PackageVenue [venueId=" + venueId + ", venueRoom=" + venueRoom + ", venueName="
        + venueName + ", roomCapacity=" + roomCapacity + ", venuePackage=" + venuePackage
        + ", venuePortofolio=" + venuePortofolio + ", venuePromo=" + venuePromo + ", discountRate="
        + discountRate + ", rentalPrice=" + rentalPrice + ", timeRent=" + timeRent + ", city="
        + city + ", paxPrice=" + paxPrice + ", minimumPayment=" + minimumPayment + ", vendor="
        + vendor + ", venueAddress=" + venueAddress + ", vendorDesc=" + vendorDesc + "]";
  }


}
