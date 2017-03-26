package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = WizardTransaction.TABLE_NAME)
public class WizardTransaction {

  public static final String TABLE_NAME = "wizard_transaction";

  @Id
  @Column(name = "transaction_id", nullable = false)
  private String transactionId;

  @Column(name = "user_id", nullable = false)
  private String userId;

  @Column(name = "status", nullable = false)
  boolean status;

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "WizardTransaction [transactionId=" + transactionId + ", userId=" + userId + ", status="
        + status + "]";
  }



}
