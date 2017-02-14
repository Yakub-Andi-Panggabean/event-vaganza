package com.special.gift.app.dto;

import com.special.gift.app.domain.User;
import com.special.gift.app.domain.Vendor;

public class BookingTransactionDto {

  private String transactionId;
  private String transactionDate;
  private String transactionTime;
  private String eventId;
  private String dateBooking;
  private String timeBooking;
  private char statusTransaction;
  private char statuspayment;
  private int priceAll;
  private char methodPayment;
  private int pricePayment;
  private User user;
  private Vendor vendor;

  public BookingTransactionDto() {
    super();
  }


  public BookingTransactionDto(String transactionId, String transactionDate, String transactionTime,
      String eventId, String dateBooking, String timeBooking, char statusTransaction,
      char statuspayment, int priceAll, char methodPayment, int pricePayment, User user,
      Vendor vendor) {
    super();
    this.transactionId = transactionId;
    this.transactionDate = transactionDate;
    this.transactionTime = transactionTime;
    this.eventId = eventId;
    this.dateBooking = dateBooking;
    this.timeBooking = timeBooking;
    this.statusTransaction = statusTransaction;
    this.statuspayment = statuspayment;
    this.priceAll = priceAll;
    this.methodPayment = methodPayment;
    this.pricePayment = pricePayment;
    this.user = user;
    this.vendor = vendor;
  }


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


  public int getPricePayment() {
    return pricePayment;
  }


  public void setPricePayment(int pricePayment) {
    this.pricePayment = pricePayment;
  }


  public User getUser() {
    return user;
  }


  public void setUser(User user) {
    this.user = user;
  }


  public Vendor getVendor() {
    return vendor;
  }


  public void setVendor(Vendor vendor) {
    this.vendor = vendor;
  }



  @Override
  public String toString() {
    return "BookingTransactionDto [transactionId=" + transactionId + ", transactionDate="
        + transactionDate + ", transactionTime=" + transactionTime + ", eventId=" + eventId
        + ", dateBooking=" + dateBooking + ", timeBooking=" + timeBooking + ", statusTransaction="
        + statusTransaction + ", statuspayment=" + statuspayment + ", priceAll=" + priceAll
        + ", methodPayment=" + methodPayment + ", pricePayment=" + pricePayment + ", user=" + user
        + ", vendor=" + vendor + "]";
  }



}
