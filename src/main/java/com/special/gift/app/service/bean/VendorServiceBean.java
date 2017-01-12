package com.special.gift.app.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.special.gift.app.domain.Vendor;
import com.special.gift.app.repository.VendorRepository;
import com.special.gift.app.service.VendorService;

@Service
public class VendorServiceBean implements VendorService {

  @Autowired
  private VendorRepository repository;

  @Override
  public void addNewUserVendor(Vendor vendor) throws Exception {
    repository.save(vendor);
  }

  @Override
  public void editUserVendor(Vendor vendor) throws Exception {
    if (repository.exists(vendor.getVendorId())) {
      repository.save(vendor);
    }
  }

  @Override
  public void deleteUserVendor(Vendor vendor) throws Exception {
    if (repository.exists(vendor.getVendorId())) {
      repository.delete(vendor);
    }

  }

  @Override
  public Page<Vendor> findAll(long start, long limit) {
    return repository.findAll(new PageRequest((int) start, (int) limit));
  }

}
