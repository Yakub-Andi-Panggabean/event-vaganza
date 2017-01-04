package com.special.gift.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

  // @Autowired
  // private UserRepository userRepository;

  // @Autowired
  // private PasswordEncoder bcryptEncoder;

  // @Autowired
  // private ApplicationMenuRepository repository;
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
      // final User user = new User();
      // user.setActive(true);
      // user.setAge(12);
      // user.setCreatedBy("Kotaro Minami");
      // user.setCreatedDate(new Date());
      // user.setEmail("yakub.jobs@gmail.com");
      // user.setFullName("the last man in the wood");
      // user.setPassword(bcryptEncoder.encode("mamaapakabar"));
      // user.setPhone("081213741988");
      // user.setUsername("yucav");


      // userRepository.save(user);
    } catch (final Exception e) {
      e.printStackTrace();
    }



  }

}
