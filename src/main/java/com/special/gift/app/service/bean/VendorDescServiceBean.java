package com.special.gift.app.service.bean;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    List<VendorDesc> contents = new ArrayList<>();

    for (final VendorDesc vendorDesc : categories) {
      if (String.valueOf(vendorDesc.getVendorType().charAt(2)).equals("0")) {
        vendorDesc.setVendorTypeName(vendorDesc.getVendorTypeName().toLowerCase());
        contents.add(vendorDesc);
      }
    }

    log.debug("parent categories size : {}", contents.size());

    if (contents.size() > 0) {

      if (limit > (contents.size() - 1)) {
        limit = contents.size();
      }

      if (start > (contents.size() - 1)) {
        start = contents.size();
      }

      log.debug("start : {}, limit : {}", start, limit);

      contents = contents.subList((int) start, (int) limit);

    }



    final Page<VendorDesc> results = new PageImpl<VendorDesc>(contents);

    return results;
  }

  @Override
  public Page<VendorDesc> findAllChildren(String parent, long start, long limit) {
    final Iterable<VendorDesc> categories = repository.findAll();

    List<VendorDesc> contents = new ArrayList<>();

    for (final VendorDesc vendorDesc : categories) {
      if (String.valueOf(vendorDesc.getVendorType().charAt(1))
          .equals(String.valueOf(parent.charAt(1)))) {
        vendorDesc.setVendorTypeName(vendorDesc.getVendorTypeName().toLowerCase());

        if (!parent.equals(vendorDesc.getVendorType()) && !String
            .valueOf(vendorDesc.getVendorType().charAt(vendorDesc.getVendorType().length() - 1))
            .equals("0")) {
          contents.add(vendorDesc);
        }
      }
    }

    log.debug("children of {} category size : {}", parent, contents.size());

    if (contents.size() > 0) {

      if (limit > (contents.size() - 1)) {
        limit = contents.size();
      }

      if (start > (contents.size() - 1)) {
        start = contents.size();
      }

      log.debug("start : {}, limit : {}", start, limit);

      contents = contents.subList((int) start, (int) limit);

    }

    final Page<VendorDesc> results = new PageImpl<VendorDesc>(contents);

    return results;
  }

  @Override
  public VendorDesc findById(String id) {
    return repository.findOne(id);
  }

  @Override
  public Page<VendorDesc> findAll() {
    return repository.findAll(new PageRequest(0, (int) repository.count()));
  }


}
