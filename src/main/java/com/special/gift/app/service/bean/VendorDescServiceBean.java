package com.special.gift.app.service.bean;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.special.gift.app.domain.VendorDesc;
import com.special.gift.app.repository.VendorDescriptionRepository;
import com.special.gift.app.service.VendorDescService;

@Service
public class VendorDescServiceBean implements VendorDescService {

  private static final Logger log = LoggerFactory.getLogger(VendorDescServiceBean.class);

  @Autowired
  private VendorDescriptionRepository repository;

  @Override
  public Page<VendorDesc> findAllParents(long start, long limit) {
    final Iterable<VendorDesc> categories = repository.findAll();

    log.debug("categories : {}", categories.toString());

    final List<VendorDesc> contents = new ArrayList<>();

    for (final VendorDesc vendorDesc : categories) {
      if (vendorDesc.getVendorType().substring(1, 2).equals("0")) {
        contents.add(vendorDesc);
      }
    }

    log.debug("parent categories size : {}", contents.size());

    if (limit > (contents.size() - 1)) {
      limit = contents.size();
    }

    log.debug("start : {}, limit : {}", start, limit);
    final Page<VendorDesc> results =
        new PageImpl<VendorDesc>(contents.subList((int) start, (int) limit));
    return results;
  }

}
