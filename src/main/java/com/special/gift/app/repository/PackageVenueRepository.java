package com.special.gift.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.PackageVenue;

public interface PackageVenueRepository extends CrudRepository<PackageVenue, String> {

  Page<PackageVenue> findAll(Pageable pageable);

  @Query(value = "select * from package_venue where vendor_id =?1", nativeQuery = true)
  PackageVenue findVenueUsingVendorId(String vendorId);

  @Query(value = "select * from package_venue where vendor_type =?1", nativeQuery = true)
  List<PackageVenue> findVenuesUsingVendorType(String vendorType);

}
