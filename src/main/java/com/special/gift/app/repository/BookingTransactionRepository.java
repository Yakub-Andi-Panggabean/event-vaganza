package com.special.gift.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.BookingTransaction;

public interface BookingTransactionRepository extends CrudRepository<BookingTransaction, String> {

  Page<BookingTransaction> findAll(Pageable pageable);

  @Query(value = "select * from booking_transaction where vendor_id=?1", nativeQuery = true)
  List<BookingTransaction> findVendorTransactions(String vendorId);

}
