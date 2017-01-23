package com.special.gift.app.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.GlobalSequence;

public interface GlobalSequenceRepository extends CrudRepository<GlobalSequence, BigInteger> {

  @Query(value = "SELECT LPAD(f_get_seq(?1),10,'0') SEQUENCE_ID", nativeQuery = true)
  String generateSequence(String sequenceId);

}
