package com.special.gift.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.special.gift.app.domain.BookingTransaction;

public interface BookingTransactionService {

  void saveBookingTransaction(BookingTransaction transaction) throws Exception;

  void updateBookingTransaction(BookingTransaction transaction) throws Exception;

  void deleteBooking(BookingTransaction transaction) throws Exception;

  void saveBookingBatch(Iterable<BookingTransaction> bookingTransactions) throws Exception;

  Page<BookingTransaction> findBookingTransaction(Pageable pageable) throws Exception;

  BookingTransaction findById(String id) throws Exception;


}
