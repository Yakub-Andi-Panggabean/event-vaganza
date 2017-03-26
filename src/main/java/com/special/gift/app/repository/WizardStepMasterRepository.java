package com.special.gift.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.WizardStepMaster;

public interface WizardStepMasterRepository extends CrudRepository<WizardStepMaster, Integer> {

  @Query(value = "select * from wizard_step_master order by step_order", nativeQuery = true)
  List<WizardStepMaster> findAllSteps();

  @Query(value = "select * from wizard_step_master where step_order = ?1", nativeQuery = true)
  WizardStepMaster findByOrder(int order);

  WizardStepMaster findByPackageCategory(String category);

}
