package com.special.gift.app.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = BookingTransaction.TABLE_NAME)
public class BookingTransaction {

  public static final String TABLE_NAME = "booking_transaction";

  @Id
  @Column(name = "transaction_id", nullable = false, length = 20)
  private String transactionId;

  @Column(name = "transaction_date", nullable = false, length = 6)
  private String transactionDate;

  @Column(name = "transaction_time", nullable = false, length = 6)
  private String transactionTime;

  @Column(name = "event_id", nullable = false, length = 10)
  private String eventId;

  @Column(name = "date_booking", nullable = false, length = 6)
  private String dateBooking;

  @Column(name = "time_booking", nullable = false, length = 6)
  private String timeBooking;

  @Column(name = "status_transaction", nullable = false, length = 6)
  private char statusTransaction;

  @Column(name = "status_payment", nullable = false)
  private char statuspayment;

  @Column(name = "price_all", nullable = false)
  private int priceAll;

  @Column(name = "methode_payment", nullable = false)
  private char methodPayment;

  @Column(name = "price_payment", nullable = false)
  private int pricePayment;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToOne
  @JoinColumns({@JoinColumn(name = "vendor_id"),
      @JoinColumn(name = "vendor_type", columnDefinition = "CHAR(3)")})
  private Vendor vendor;

  @OneToOne(targetEntity = TransactionConfirmation.class, cascade = CascadeType.ALL,
      mappedBy = "transaction")
  private TransactionConfirmation transactionConfirmation;

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

  public TransactionConfirmation getTransactionConfirmation() {
    return transactionConfirmation;
  }

  public void setTransactionConfirmation(TransactionConfirmation transactionConfirmation) {
    this.transactionConfirmation = transactionConfirmation;
  }



}
