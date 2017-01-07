package com.special.gift.app.service;

import org.springframework.data.domain.Page;

import com.special.gift.app.domain.VendorDesc;

public interface VendorDescService {


  Page<VendorDesc> findAllParents(long start, long limit);

}
