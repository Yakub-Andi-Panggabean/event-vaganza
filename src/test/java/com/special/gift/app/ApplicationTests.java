package com.special.gift.app;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.special.gift.app.domain.ApplicationMenu;
import com.special.gift.app.repository.ApplicationMenuRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {


  @Autowired
  private ApplicationMenuRepository repository;

  @Test
  public void insertApplicationMenu() {

    final ApplicationMenu applicationMenu = new ApplicationMenu();
    applicationMenu.setActive(true);
    applicationMenu.setChildren(null);
    applicationMenu.setCreatedBy("kotaro minamis");
    applicationMenu.setCreatedDate(new Date());
    applicationMenu.setDescription("daftar");
    applicationMenu.setLabel("Daftar");
    applicationMenu.setParent(null);
    applicationMenu.setRole(null);
    applicationMenu.setUrl("/help");
    repository.save(applicationMenu);

  }

}
