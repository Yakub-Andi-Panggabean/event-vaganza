package com.special.gift.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.WizardTransaction;

public interface WizardTransactionRepository extends CrudRepository<WizardTransaction, String> {

  @Query(value = "select transaction_id from wizard_transaction where status=false and user_id=?1",
      nativeQuery = true)
  String findUnfinishedTransaction(String userId);

}
