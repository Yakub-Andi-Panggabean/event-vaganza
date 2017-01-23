package com.special.gift.app.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.special.gift.app.repository.GlobalSequenceRepository;
import com.special.gift.app.service.SequenceService;

@Service
public class SequenceServiceBean implements SequenceService {

  @Autowired
  private GlobalSequenceRepository repository;


  @Override
  public String generateSequence(String sequenceId) {
    return repository.generateSequence(sequenceId);
  }

}
