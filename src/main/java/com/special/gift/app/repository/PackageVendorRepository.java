package com.special.gift.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.PackageVendor;
import com.special.gift.app.domain.PackageVenue;

public interface PackageVendorRepository extends CrudRepository<PackageVendor, String> {

  Page<PackageVenue> findAll(Pageable pageable);

}
