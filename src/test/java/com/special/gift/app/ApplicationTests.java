package com.special.gift.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.special.gift.app.domain.User;
import com.special.gift.app.repository.UserRepository;
import com.special.gift.app.util.CommonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ShaPasswordEncoder passwordEncoder;

  //
  // @Test
  // public void insertApplicationMenu() {
  //
  // final ApplicationMenu applicationMenu = new ApplicationMenu();
  // applicationMenu.setActive(true);
  // applicationMenu.setChildren(null);
  // applicationMenu.setCreatedBy("kotaro minami");
  // applicationMenu.setCreatedDate(new Date());
  // applicationMenu.setDescription("register page");
  // applicationMenu.setLabel("Daftar");
  // applicationMenu.setParent(null);
  // applicationMenu.setRole(null);
  // applicationMenu.setUrl("/register");
  // repository.save(applicationMenu);
  //
  // }

  @Test
  public void insertUser() {



    try {
      final User user = new User();
      user.setEmail("yakub.jobs@gmail.com");
      user.setHandphone("081213741988");
      user.setPassword(passwordEncoder.encodePassword("mamaapakabar", CommonUtil.SALT));
      user.setPhone("0212067899");
      user.setStatus('1');
      user.setUserAddress("pandawa streen no 312");
      user.setUserId("1000000001");
      user.setUsername("yakub");
      user.setUserZip("30118");
      user.setVendor(null);
      userRepository.save(user);
    } catch (final Exception e) {
      e.printStackTrace();
    }



  }

}
