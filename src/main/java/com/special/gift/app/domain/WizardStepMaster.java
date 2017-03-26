package com.special.gift.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = WizardStepMaster.TABLE_NAME)
public class WizardStepMaster {

  public static final String TABLE_NAME = "WIZARD_STEP_MASTER";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private int id;

  @Column(name = "step_name", nullable = false)
  private String stepName;

  @Column(name = "step_order", nullable = false, unique = true)
  private int stepOrder;

  @Column(name = "vendor_category", nullable = false, unique = true)
  private String packageCategory;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStepName() {
    return stepName;
  }

  public void setStepName(String stepName) {
    this.stepName = stepName;
  }

  public int getStepOrder() {
    return stepOrder;
  }

  public void setStepOrder(int stepOrder) {
    this.stepOrder = stepOrder;
  }

  public String getPackageCategory() {
    return packageCategory;
  }

  public void setPackageCategory(String packageCategory) {
    this.packageCategory = packageCategory;
  }

  @Override
  public String toString() {
    return "WizardStepMaster [id=" + id + ", stepName=" + stepName + ", stepOrder=" + stepOrder
        + ", packageCategory=" + packageCategory + "]";
  }



}
