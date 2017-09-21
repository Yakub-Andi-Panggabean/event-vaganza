package com.special.gift.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.PackageVendor;

public interface PackageVendorRepository
    extends CrudRepository<PackageVendor, String>, JpaSpecificationExecutor<PackageVendor> {

  Page<PackageVendor> findAll(Pageable pageable);

  @Override
  Page<PackageVendor> findAll(Specification<PackageVendor> spec, Pageable pageable);

  @Query(
      value = "SELECT a.* FROM  package_vendor a, vendor b WHERE a.vendor_id=b.vendor_id AND b.vendor_type like ?1% AND b.vendor_id_venue like %?2% AND b.vendor_address like %?3%",
      nativeQuery = true)
  List<PackageVendor> findPackageByCategoryAndVenue(String category, String venue, String city);

}
