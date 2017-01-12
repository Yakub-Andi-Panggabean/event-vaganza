package com.special.gift.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.Vendor;
import com.special.gift.app.domain.VendorId;

public interface VendorRepository extends CrudRepository<Vendor, VendorId> {

  Page<Vendor> findAll(Pageable pageable);

}
