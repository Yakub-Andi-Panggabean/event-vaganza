package com.special.gift.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.User;
import com.special.gift.app.domain.Vendor;
import com.special.gift.app.domain.VendorId;

public interface VendorRepository extends CrudRepository<Vendor, VendorId> {

  Page<Vendor> findAll(Pageable pageable);

  List<Vendor> findByUser(User user);

  void deleteByUser(User user);

  @Query(value = "select * from vendor where vendor_id=?1 limit 1", nativeQuery = true)
  Vendor findSingleVendorById(String id);

  @Query(value = "select * from vendor where vendor_id_venue=?1 ", nativeQuery = true)
  Vendor findVendorsUsingVenue(String venue);

}
