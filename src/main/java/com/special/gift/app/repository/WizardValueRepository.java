package com.special.gift.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.WizardValue;

public interface WizardValueRepository extends CrudRepository<WizardValue, String> {

  @Query(value = "select * from wizard_value where status_false", nativeQuery = true)
  WizardValue findUnfinishedWizardProcess(String userId);


}
