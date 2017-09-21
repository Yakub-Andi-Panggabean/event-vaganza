package com.special.gift.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.WizardValue;

public interface WizardValueRepository extends CrudRepository<WizardValue, String> {

  @Query(value = "select * from wizard_value where status_false", nativeQuery = true)
  WizardValue findUnfinishedWizardProcess(String userId);

  @Query(value = "update wizard_value set content=?1 where transaction_id=?2", nativeQuery = true)
  void updateContentValue(String content, String transactionId);


}
