package com.special.gift.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.BookingTransaction;

public interface BookingTransactionRepository extends CrudRepository<BookingTransaction, String> {

}
