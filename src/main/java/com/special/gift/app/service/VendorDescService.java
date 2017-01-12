package com.special.gift.app.service;

import org.springframework.data.domain.Page;

import com.special.gift.app.domain.VendorDesc;

public interface VendorDescService {


  Page<VendorDesc> findAll();

  Page<VendorDesc> findAllParents(long start, long limit);

  Page<VendorDesc> findAllChildren(String parent, long start, long limit);

  VendorDesc findById(String id);
}
