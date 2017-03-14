package com.special.gift.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.special.gift.app.domain.TransactionConfirmation;

@Repository
public interface TransactionConfirmationRepository
    extends CrudRepository<TransactionConfirmation, String> {



}
