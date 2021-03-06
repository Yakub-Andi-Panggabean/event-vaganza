package com.special.gift.app.service.bean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special.gift.app.domain.User;
import com.special.gift.app.domain.Vendor;
import com.special.gift.app.domain.VendorId;
import com.special.gift.app.dto.VendorDto;
import com.special.gift.app.repository.VendorRepository;
import com.special.gift.app.service.UserService;
import com.special.gift.app.service.VendorService;

@Service
@Transactional(readOnly = true)
public class VendorServiceBean implements VendorService {

  private static final Logger log = LoggerFactory.getLogger(VendorServiceBean.class);

  @Autowired
  private VendorRepository repository;

  @Autowired
  private UserService userService;

  @Override
  @Transactional(readOnly = false)
  public void addNewUserVendor(Vendor vendor) throws Exception {
    repository.save(vendor);
  }

  @Override
  @Transactional(readOnly = false)
  public void editUserVendor(Vendor vendor) throws Exception {
    if (repository.exists(vendor.getVendorId())) {
      repository.save(vendor);
    }
  }

  @Override
  @Transactional(readOnly = false)
  public void deleteUserVendor(Vendor vendor) throws Exception {
    if (repository.exists(vendor.getVendorId())) {
      repository.delete(vendor);
    }

  }

  @Override
  public Page<Vendor> findAll(long start, long limit) {
    return repository.findAll(new PageRequest((int) start, (int) limit));
  }

  @Override
  public List<Vendor> findByUser(User user) {
    return repository.findByUser(user);
  }

  @Override
  public void deleteByUser(User user) throws Exception {
    repository.deleteByUser(user);
  }

  @Override
  @Transactional(readOnly = false)
  public void updateVendor(String userId, String vendorSequenceId, VendorDto vendor)
      throws Exception {
    try {
      vendor.setUser(userId);
      final User user = userService.findUserByPrincipal(vendor.getUser());
      if (user != null) {
        log.debug("user : {}", user.toString());
        Vendor target = null;
        VendorId vendorId = null;


        if (vendor.getVendorType() != null) {
          deleteByUser(user);
          for (final String type : vendor.getVendorType().split(",")) {
            target = new Vendor();

            vendorId = new VendorId();
            vendorId.setType(type);
            vendorId.setVendorId(vendorSequenceId);

            BeanUtils.copyProperties(vendor, target);

            target.setVendorId(vendorId);
            target.setUser(user);

            addNewUserVendor(target);
          }
        }


      }
    } catch (final Exception exception) {
      throw exception;
    }
  }


}
