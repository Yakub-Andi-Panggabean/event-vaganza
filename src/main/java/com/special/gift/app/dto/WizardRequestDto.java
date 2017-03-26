package com.special.gift.app.dto;

import java.io.Serializable;

public class WizardRequestDto implements Serializable {



  private static final long serialVersionUID = 5852563812565529420L;

  private int step;
  private String userId;
  private String transactionId;
  private String value;
  private boolean isComplete;
  private boolean isToNext;

  public WizardRequestDto() {
    super();
  }

  public int getStep() {
    return step;
  }

  public void setStep(int step) {
    this.step = step;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public boolean getIsComplete() {
    return isComplete;
  }

  public void setIsComplete(boolean isComplete) {
    this.isComplete = isComplete;
  }

  public boolean getIsToNext() {
    return isToNext;
  }

  public void setIsToNext(boolean isToNext) {
    this.isToNext = isToNext;
  }

  @Override
  public String toString() {
    return "WizardRequestDto [step=" + step + ", userId=" + userId + ", transactionId="
        + transactionId + ", value=" + value + ", isComplete=" + isComplete + ", isToNext="
        + isToNext + "]";
  }



}
