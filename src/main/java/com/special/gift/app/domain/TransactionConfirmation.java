package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = TransactionConfirmation.TABLE_NAME)
public class TransactionConfirmation {

  public static final String TABLE_NAME = "need_confirmation";

  @Id
  @Column(name = "transaction_id", nullable = false, length = 10)
  private String transactionId;

  @Column(name = "group_transaction_id", nullable = false)
  private String groupTransactionId;

  @Column(name = "status", nullable = false)
  private char status;

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

  public String getGroupTransactionId() {
    return groupTransactionId;
  }

  public void setGroupTransactionId(String groupTransactionId) {
    this.groupTransactionId = groupTransactionId;
  }



}
