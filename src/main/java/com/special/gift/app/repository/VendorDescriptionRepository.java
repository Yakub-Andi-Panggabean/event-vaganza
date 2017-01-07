package com.special.gift.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.VendorDesc;

public interface VendorDescriptionRepository extends CrudRepository<VendorDesc, String> {

  Page<VendorDesc> findAll(Pageable pageable);

}
