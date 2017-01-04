package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = TransactionConfirmation.TABLE_NAME)
public class TransactionConfirmation {

  public static final String TABLE_NAME = "need_confirm_transaction";

  @Id
  @Column(name = "transaction_id", nullable = false, length = 10)
  private String transactionId;

  @Column(name = "status", nullable = false)
  private char status;

  @JoinColumn(name = "transaction_id")
  @OneToOne
  @MapsId
  private BookingTransaction transaction;

  public BookingTransaction getTransaction() {
    return transaction;
  }

  public void setTransaction(BookingTransaction transaction) {
    this.transaction = transaction;
  }

  public char getStatus() {
    return status;
  }

  public void setStatus(char status) {
    this.status = status;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }



}
