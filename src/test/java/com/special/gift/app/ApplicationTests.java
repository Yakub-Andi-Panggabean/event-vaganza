package com.special.gift.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.special.gift.app.domain.PackageVendor;
import com.special.gift.app.domain.User;
import com.special.gift.app.domain.Vendor;
import com.special.gift.app.domain.VendorId;
import com.special.gift.app.repository.GlobalSequenceRepository;
import com.special.gift.app.repository.PackageVendorRepository;
import com.special.gift.app.repository.UserRepository;
import com.special.gift.app.repository.VendorRepository;
import com.special.gift.app.util.CommonUtil;
import com.special.gift.app.util.SequenceUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

  private static final Logger log = LoggerFactory.getLogger(ApplicationTests.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private VendorRepository vendorRepository;

  @Autowired
  private ShaPasswordEncoder passwordEncoder;

  @Autowired
  private PackageVendorRepository pVendorRepsitory;

  @Autowired
  private GlobalSequenceRepository sequenceRepository;

  @Test
  public void testSequence() {
    try {
      log.debug("sequence : {}", sequenceRepository.generateSequence(SequenceUtil.VENDOR_ID_SEQ));
    } catch (final Exception ex) {
      ex.printStackTrace();
    }
  }

  // @Test
  public void insertUser() {
    try {
      final User user = new User();
      user.setEmail("yakub.jobs@gmail.com");
      user.setHandphone("081213741988");
      user.setPassword(passwordEncoder.encodePassword("mamaapakabar", CommonUtil.SALT));
      user.setPhone("0212067899");
      user.setStatus('1');
      user.setUserAddress("pandawa streen no 312");
      user.setUserId(sequenceRepository.generateSequence(SequenceUtil.USER_ID_SEQ));
      user.setUsername("yakub");
      user.setUserZip("30118");
      user.setVendor(null);
      userRepository.save(user);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }


  // @Test
  public void insertPackageVendor() {

    try {
      final PackageVendor pVendor = new PackageVendor();
      pVendor
          .setPackageImg("http://blog.room34.com/wp-content/uploads/underdog/logo.thumbnail.png");
      pVendor.setDiscountRate(3);
      pVendor.setMinimumPayment(200000000);
      pVendor.setPackageCapacity(100);
      pVendor.setPackageCategory("xxx");
      pVendor.setPackageDesc("Package Description");
      pVendor.setPackageId("50000000");
      pVendor.setPackageName("Package Example");
      pVendor.setPackagePrice(300000000);
      pVendor.setPackagePromo("get one chair");
      pVendor.setPackageStyle("free style");
      pVendor.setTimePackage("4");

      final VendorId id = new VendorId();
      id.setType("80");
      id.setVendorId("5000000000");

      pVendor.setVendor(vendorRepository.findOne(id));


      pVendorRepsitory.save(pVendor);
    } catch (final Exception ex) {
      ex.printStackTrace();
    }

  }

  // @Test
  public void selectVendor() {
    try {
      final Vendor vendor = vendorRepository.findOne(new VendorId("5000000000", "80"));
      log.debug("vendor : {}", vendor.toString());
    } catch (final Exception ex) {
      ex.printStackTrace();
    }
  }

}
