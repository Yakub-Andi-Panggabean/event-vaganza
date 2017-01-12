package com.special.gift.app.service;

import org.springframework.data.domain.Page;

import com.special.gift.app.domain.Vendor;

public interface VendorService {

  public void addNewUserVendor(Vendor vendor) throws Exception;

  public void editUserVendor(Vendor vendor) throws Exception;

  public void deleteUserVendor(Vendor vendor) throws Exception;

  Page<Vendor> findAll(long start, long limit);

}
