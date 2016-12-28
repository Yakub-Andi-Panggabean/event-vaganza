package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = TransactionConfirmation.TABLE_NAME)
public class TransactionConfirmation {

  public static final String TABLE_NAME = "need_confirm_transaction";

  @Column(name = "transaction_id", nullable = false, length = 20)
  private String transactionId;
  @Column(name = "status", nullable = false)
  private char status;

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public char getStatus() {
    return status;
  }

  public void setStatus(char status) {
    this.status = status;
  }



}
