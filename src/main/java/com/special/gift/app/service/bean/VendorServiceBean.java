package com.special.gift.app.service.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special.gift.app.domain.BookingTransaction;
import com.special.gift.app.domain.TransactionConfirmation;
import com.special.gift.app.domain.User;
import com.special.gift.app.domain.Vendor;
import com.special.gift.app.domain.VendorId;
import com.special.gift.app.dto.VendorDto;
import com.special.gift.app.repository.BookingTransactionRepository;
import com.special.gift.app.repository.TransactionConfirmationRepository;
import com.special.gift.app.repository.VendorRepository;
import com.special.gift.app.service.UserService;
import com.special.gift.app.service.VendorService;

@Service
@Transactional(readOnly = true)
public class VendorServiceBean implements VendorService {

  private static final Logger log = LoggerFactory.getLogger(VendorServiceBean.class);

  @Autowired
  private VendorRepository repository;

  @Autowired
  private UserService userService;

  @Autowired
  private BookingTransactionRepository transactionRepository;

  @Autowired
  private TransactionConfirmationRepository confirmationRepository;

  SimpleDateFormat formatFrom = new SimpleDateFormat("yyyyMMdd");
  SimpleDateFormat formatTo = new SimpleDateFormat("dd MMMM yyyy");

  @Override
  @Transactional(readOnly = false)
  public void addNewUserVendor(Vendor vendor) throws Exception {
    repository.save(vendor);
  }

  @Override
  @Transactional(readOnly = false)
  public void editUserVendor(Vendor vendor) throws Exception {
    if (repository.exists(vendor.getVendorId())) {
      repository.save(vendor);
    }
  }

  @Override
  @Transactional(readOnly = false)
  public void deleteUserVendor(Vendor vendor) throws Exception {
    if (repository.exists(vendor.getVendorId())) {
      repository.delete(vendor);
    }

  }

  @Override
  public Page<Vendor> findAll(long start, long limit) {
    return repository.findAll(new PageRequest((int) start, (int) limit));
  }

  @Override
  public List<Vendor> findByUser(User user) {
    return repository.findByUser(user);
  }

  @Override
  public void deleteByUser(User user) throws Exception {
    repository.deleteByUser(user);
  }

  @Override
  @Transactional(readOnly = false)
  public void updateVendor(String userId, String vendorSequenceId, VendorDto vendor)
      throws Exception {
    try {
      vendor.setUser(userId);
      final User user = userService.findUserByPrincipal(vendor.getUser());
      if (user != null) {
        log.debug("user : {}", user.toString());
        Vendor target = null;
        VendorId vendorId = null;


        if (vendor.getVendorType() != null) {
          deleteByUser(user);
          for (final String type : vendor.getVendorType().split(",")) {
            target = new Vendor();

            vendorId = new VendorId();
            vendorId.setType(type);
            vendorId.setVendorId(vendorSequenceId);

            BeanUtils.copyProperties(vendor, target);

            target.setVendorId(vendorId);
            target.setUser(user);

            addNewUserVendor(target);
          }
        }


      }
    } catch (final Exception exception) {
      throw exception;
    }
  }

  @Override
  public Vendor findById(VendorId id) {
    return repository.findOne(id);
  }

  @Override
  public Vendor findBySingleId(String id) {
    return repository.findSingleVendorById(id);
  }

  @Override
  public List<BookingTransaction> getVendorConfirmations(String vendorId) throws Exception {
    final List<BookingTransaction> BookingTransaction =
        transactionRepository.findVendorTransactions(vendorId);

    final List<BookingTransaction> unconfirmedTransaction = new ArrayList<>();

    for (final BookingTransaction trans : BookingTransaction) {
      for (final TransactionConfirmation confirm : confirmationRepository.findAll()) {
        if (confirm.getStatus() == '0'
            && confirm.getTransactionId().equals(trans.getTransactionId())) {
          trans.setDateBooking(formatTo.format(formatFrom.parse(trans.getTransactionDate())));
          unconfirmedTransaction.add(trans);
        }
      }

    }

    log.debug("booked : {}", unconfirmedTransaction.toString());
    return unconfirmedTransaction;
  }

  @Override
  @Transactional(readOnly = false)
  public void confirmBooking(String transactionId) {

    if (confirmationRepository.exists(transactionId)) {
      final TransactionConfirmation tx = confirmationRepository.findOne(transactionId);
      tx.setStatus('1');
      confirmationRepository.save(tx);
    } else {
      log.debug("no record found for transaction id : {}", transactionId);
    }


  }



}
