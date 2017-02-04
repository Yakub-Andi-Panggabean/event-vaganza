package com.special.gift.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.PackageVendor;

public interface PackageVendorRepository
    extends CrudRepository<PackageVendor, String>, JpaSpecificationExecutor<PackageVendor> {

  Page<PackageVendor> findAll(Pageable pageable);

  @Override
  Page<PackageVendor> findAll(Specification<PackageVendor> spec, Pageable pageable);

}
