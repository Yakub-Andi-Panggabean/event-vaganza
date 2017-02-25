package com.special.gift.app.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.special.gift.app.repository.GlobalSequenceRepository;
import com.special.gift.app.service.SequenceService;
import com.special.gift.app.util.SequenceUtil;

@Service
public class SequenceServiceBean implements SequenceService {

  @Autowired
  private GlobalSequenceRepository repository;


  @Override
  public String generateSequence(String sequenceId) {
    if (sequenceId.equals(SequenceUtil.TRANSACTION_ID_SEQ)
        || sequenceId.equals(SequenceUtil.GROUP_TRANSACTION_ID_SEQ)) {
      return repository.generateTransactionSequence(sequenceId);
    } else {
      return repository.generateSequence(sequenceId);
    }
  }

}
