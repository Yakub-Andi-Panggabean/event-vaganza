package com.special.gift.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.special.gift.app.domain.User;
import com.special.gift.app.domain.Vendor;
import com.special.gift.app.domain.VendorId;
import com.special.gift.app.dto.VendorDto;

public interface VendorService {

  void addNewUserVendor(Vendor vendor) throws Exception;

  void editUserVendor(Vendor vendor) throws Exception;

  void deleteUserVendor(Vendor vendor) throws Exception;

  void deleteByUser(User user) throws Exception;

  void updateVendor(String userId, String vendorSequenceId, VendorDto vendor) throws Exception;

  Page<Vendor> findAll(long start, long limit);

  List<Vendor> findByUser(User user);

  Vendor findById(VendorId id);

}
