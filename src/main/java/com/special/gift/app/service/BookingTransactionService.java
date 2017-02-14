package com.special.gift.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.special.gift.app.domain.BookingTransaction;

public interface BookingTransactionService {

  void saveBookingTransaction(BookingTransaction transaction);

  void updateBookingTransaction(BookingTransaction transaction);

  void deleteBooking(BookingTransaction transaction);

  Page<BookingTransaction> findBookingTransaction(Pageable pageable);

  BookingTransaction findById(String id);


}
