package com.special.gift.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = GlobalSequence.GLOBAL_SEQUENCE_TABLE)
public class GlobalSequence implements Serializable {

  private static final long serialVersionUID = -1140311619462758849L;
  public static final String GLOBAL_SEQUENCE_TABLE = "global_sequences";

  @Id
  @Column(name = "SEQ_ID", nullable = false, length = 50)
  private String sequenceId;

  @Column(name = "SEQ_CURRVALUE", nullable = false, columnDefinition = "bigint(15)")
  private Long currentValue;

  @Column(name = "SEQ_INTERVAL", nullable = false)
  private int sequenceInterval;

  @Column(name = "SEQ_NEXTVALUE", nullable = false, columnDefinition = "bigint(15)")
  private Long nextValue;

  public String getSequenceId() {
    return sequenceId;
  }

  public void setSequenceId(String sequenceId) {
    this.sequenceId = sequenceId;
  }

  public int getSequenceInterval() {
    return sequenceInterval;
  }

  public void setSequenceInterval(int sequenceInterval) {
    this.sequenceInterval = sequenceInterval;
  }

  public Long getCurrentValue() {
    return currentValue;
  }

  public void setCurrentValue(Long currentValue) {
    this.currentValue = currentValue;
  }

  public Long getNextValue() {
    return nextValue;
  }

  public void setNextValue(Long nextValue) {
    this.nextValue = nextValue;
  }



}
