package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = BookingTransaction.TABLE_NAME)
public class BookingTransaction {

  public static final String TABLE_NAME = "booking_transaction";

  @Column(name = "transaction_id", nullable = false, length = 20)
  private String transactionId;
  @Column(name = "transaction_date", nullable = false, length = 6)
  private String transactionDate;
  @Column(name = "transaction_time", nullable = false, length = 6)
  private String transactionTime;
  @Column(name = "user_id", nullable = false, length = 10)
  private String userId;
  @Column(name = "vendor_id", nullable = false, length = 10)
  private String vendorId;
  @Column(name = "event_id", nullable = false, length = 10)
  private String eventId;
  @Column(name = "date_booking", nullable = false, length = 6)
  private String dateBooking;
  @Column(name = "time_booking", nullable = false, length = 6)
  private String timeBooking;
  @Column(name = "date_booking", nullable = false, length = 6)
  private char statusTransaction;
  @Column(name = "status_payment", nullable = false)
  private char statuspayment;
  @Column(name = "price_all", nullable = false)
  private int priceAll;
  @Column(name = "method_payment", nullable = false)
  private char methodPayment;
  @Column(name = "vendor_type", nullable = false, length = 2)
  private String vendorType;
  @Column(name = "vendor_type", nullable = false)
  private int pricePayment;

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  public String getTransactionTime() {
    return transactionTime;
  }

  public void setTransactionTime(String transactionTime) {
    this.transactionTime = transactionTime;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getVendorId() {
    return vendorId;
  }

  public void setVendorId(String vendorId) {
    this.vendorId = vendorId;
  }

  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
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

  public char getStatusTransaction() {
    return statusTransaction;
  }

  public void setStatusTransaction(char statusTransaction) {
    this.statusTransaction = statusTransaction;
  }

  public char getStatuspayment() {
    return statuspayment;
  }

  public void setStatuspayment(char statuspayment) {
    this.statuspayment = statuspayment;
  }

  public int getPriceAll() {
    return priceAll;
  }

  public void setPriceAll(int priceAll) {
    this.priceAll = priceAll;
  }

  public char getMethodPayment() {
    return methodPayment;
  }

  public void setMethodPayment(char methodPayment) {
    this.methodPayment = methodPayment;
  }

  public String getVendorType() {
    return vendorType;
  }

  public void setVendorType(String vendorType) {
    this.vendorType = vendorType;
  }

  public int getPricePayment() {
    return pricePayment;
  }

  public void setPricePayment(int pricePayment) {
    this.pricePayment = pricePayment;
  }



}
