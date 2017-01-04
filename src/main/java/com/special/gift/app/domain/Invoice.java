package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = Invoice.TABLE_NAME)
public class Invoice {

  public static final String TABLE_NAME = "invoice_user";

  @Id
  @Column(name = "no_invoice", nullable = false, length = 10)
  private String invoiceNo;

  @Column(name = "date_booking", nullable = false, length = 8)
  private String dateBooking;

  @Column(name = "time_booking", nullable = false, length = 6)
  private String timeBooking;

  @Column(name = "name_booking", nullable = false, length = 50)
  private String nameBooking;

  @Column(name = "event_type", nullable = false, length = 20)
  private String eventType;

  @Column(name = "event_place", nullable = false, length = 50)
  private String eventPlace;

  @Column(name = "address_booking", nullable = false, length = 100)
  private String addressBooking;

  @Column(name = "style_event", nullable = false, length = 10)
  private String eventStyle;

  @Column(name = "type_decoration", nullable = false, length = 20)
  private String decorationType;

  @Column(name = "price", nullable = false)
  private int price;

  @Column(name = "total_price", nullable = false)
  private int totalPrice;

  @OneToOne
  @JoinColumn(name = "transaction_id")
  private BookingTransaction transaction;

  public String getInvoiceNo() {
    return invoiceNo;
  }

  public void setInvoiceNo(String invoiceNo) {
    this.invoiceNo = invoiceNo;
  }

  public String getDateBooking() {
    return dateBooking;
  }

  public void setDateBooking(String dateBooking) {
    this.dateBooking = dateBooking;
  }

  public String getTimeBooking() {
    return timeBooking;
  }

  public void setTimeBooking(String timeBooking) {
    this.timeBooking = timeBooking;
  }

  public String getNameBooking() {
    return nameBooking;
  }

  public void setNameBooking(String nameBooking) {
    this.nameBooking = nameBooking;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public String getEventPlace() {
    return eventPlace;
  }

  public void setEventPlace(String eventPlace) {
    this.eventPlace = eventPlace;
  }

  public String getAddressBooking() {
    return addressBooking;
  }

  public void setAddressBooking(String addressBooking) {
    this.addressBooking = addressBooking;
  }

  public String getEventStyle() {
    return eventStyle;
  }

  public void setEventStyle(String eventStyle) {
    this.eventStyle = eventStyle;
  }

  public String getDecorationType() {
    return decorationType;
  }

  public void setDecorationType(String decorationType) {
    this.decorationType = decorationType;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }

  public BookingTransaction getTransaction() {
    return transaction;
  }

  public void setTransaction(BookingTransaction transaction) {
    this.transaction = transaction;
  }



}
