package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = WizardValue.TABLE_NAME)
public class WizardValue {

  public static final String TABLE_NAME = "wizard_value";

  @Id
  @Column(name = "transaction_id", nullable = false)
  private String transactionId;

  @Column(name = "content", nullable = false)
  private String content;

  @Column(name = "current_step_id", nullable = false)
  private int currentStepId;

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }



  public int getCurrentStepId() {
    return currentStepId;
  }

  public void setCurrentStepId(int currentStepId) {
    this.currentStepId = currentStepId;
  }

  @Override
  public String toString() {
    return "WizardValue [transactionId=" + transactionId + ", content=" + content
        + ", currentStepId=" + currentStepId + "]";
  }



}
