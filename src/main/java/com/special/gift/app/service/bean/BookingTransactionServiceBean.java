package com.special.gift.app.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special.gift.app.domain.BookingTransaction;
import com.special.gift.app.repository.BookingTransactionRepository;
import com.special.gift.app.service.BookingTransactionService;

@Service
@Transactional(readOnly = true)
public class BookingTransactionServiceBean implements BookingTransactionService {

  @Autowired
  private BookingTransactionRepository repository;


  @Override
  @Transactional(readOnly = false)
  public void saveBookingTransaction(BookingTransaction transaction) {
    repository.save(transaction);
  }

  @Override
  @Transactional(readOnly = false)
  public void updateBookingTransaction(BookingTransaction transaction) {
    if (repository.exists(transaction.getEventId())) {
      repository.save(transaction);
    }
  }

  @Override
  @Transactional(readOnly = false)
  public void deleteBooking(BookingTransaction transaction) {
    repository.delete(transaction);

  }

  @Override
  public Page<BookingTransaction> findBookingTransaction(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public BookingTransaction findById(String id) {
    return repository.findOne(id);
  }

}
