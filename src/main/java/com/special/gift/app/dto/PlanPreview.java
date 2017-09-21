package com.special.gift.app.dto;

public class PlanPreview {

  private int stepOrder;
  private String stepName;
  private ItemListDto chosenItem;

  public PlanPreview() {
    super();
    // TODO Auto-generated constructor stub
  }

  public int getStepOrder() {
    return stepOrder;
  }

  public void setStepOrder(int stepOrder) {
    this.stepOrder = stepOrder;
  }

  public String getStepName() {
    return stepName;
  }

  public void setStepName(String stepName) {
    this.stepName = stepName;
  }

  public ItemListDto getChosenItem() {
    return chosenItem;
  }

  public void setChosenItem(ItemListDto chosenItem) {
    this.chosenItem = chosenItem;
  }

  @Override
  public String toString() {
    return "PlanPreview [stepOrder=" + stepOrder + ", stepName=" + stepName + ", chosenItem="
        + chosenItem + "]";
  }



}
