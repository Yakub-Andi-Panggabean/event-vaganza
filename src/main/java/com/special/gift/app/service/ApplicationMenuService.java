package com.special.gift.app.service;

import java.util.List;

import com.special.gift.app.domain.ApplicationMenu;

public interface ApplicationMenuService {

  List<ApplicationMenu> fetchApplicationMenu();

  List<ApplicationMenu> fetchApplicationMenuByRole(String roleId);

  void save(ApplicationMenu applicationMenu);

  ApplicationMenu findById(String id);

  void update(ApplicationMenu applicationMenu);

}
