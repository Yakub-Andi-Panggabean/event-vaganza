package com.special.gift.app.service.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special.gift.app.domain.BookingTransaction;
import com.special.gift.app.domain.TransactionConfirmation;
import com.special.gift.app.repository.BookingTransactionRepository;
import com.special.gift.app.repository.TransactionConfirmationRepository;
import com.special.gift.app.service.BookingTransactionService;

@Service
@Transactional(readOnly = true)
public class BookingTransactionServiceBean implements BookingTransactionService {

  @Autowired
  private BookingTransactionRepository repository;

  @Autowired
  private TransactionConfirmationRepository confirmationRepository;


  @Override
  @Transactional(readOnly = false)
  public void saveBookingTransaction(BookingTransaction transaction) throws Exception {
    final TransactionConfirmation confirmation = new TransactionConfirmation();
    confirmation.setStatus('0');
    confirmation.setTransactionId(transaction.getTransactionId());
    repository.save(transaction);
    confirmationRepository.save(confirmation);
  }

  @Override
  @Transactional(readOnly = false)
  public void updateBookingTransaction(BookingTransaction transaction) throws Exception {
    if (repository.exists(transaction.getEventId())) {
      repository.save(transaction);
    }
  }

  @Override
  @Transactional(readOnly = false)
  public void deleteBooking(BookingTransaction transaction) throws Exception {
    repository.delete(transaction);

  }

  @Override
  public Page<BookingTransaction> findBookingTransaction(Pageable pageable) throws Exception {
    return repository.findAll(pageable);
  }

  @Override
  public BookingTransaction findById(String id) throws Exception {
    return repository.findOne(id);
  }

  @Transactional(readOnly = false)
  @Override
  public void saveBookingBatch(Iterable<BookingTransaction> bookingTransactions) throws Exception {
    repository.save(bookingTransactions);
    final List<TransactionConfirmation> confirmations = new ArrayList<>();
    TransactionConfirmation confirm = null;
    for (final BookingTransaction transaction : bookingTransactions) {
      confirm = new TransactionConfirmation();
      confirm.setStatus('0');
      confirm.setTransactionId(transaction.getTransactionId());
      confirmations.add(confirm);
    }
    confirmationRepository.save(confirmations);
  }

}
