package com.special.gift.app.dto;

public class WizardStateDto {

  private String transactionId;
  private int previousStep;
  private int currentStep;
  private int nextStep;


  public WizardStateDto() {
    super();
    // TODO Auto-generated constructor stub
  }



  public WizardStateDto(String transactionId, int previousStep, int currentStep, int nextStep) {
    super();
    this.transactionId = transactionId;
    this.previousStep = previousStep;
    this.currentStep = currentStep;
    this.nextStep = nextStep;
  }



  public String getTransactionId() {
    return transactionId;
  }


  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }


  public int getPreviousStep() {
    return previousStep;
  }


  public void setPreviousStep(int previousStep) {
    this.previousStep = previousStep;
  }


  public int getCurrentStep() {
    return currentStep;
  }


  public void setCurrentStep(int currentStep) {
    this.currentStep = currentStep;
  }


  public int getNextStep() {
    return nextStep;
  }


  public void setNextStep(int nextStep) {
    this.nextStep = nextStep;
  }


  @Override
  public String toString() {
    return "WizardStateDto [transactionId=" + transactionId + ", previousStep=" + previousStep
        + ", currentStep=" + currentStep + ", nextStep=" + nextStep + "]";
  }



}
