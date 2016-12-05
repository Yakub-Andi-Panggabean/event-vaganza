package com.special.gift.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.ApplicationMenu;
import com.special.gift.app.domain.Role;

public interface ApplicationMenuRepository extends CrudRepository<ApplicationMenu, String> {

  List<ApplicationMenu> findByParent(ApplicationMenu parent);

  Page<ApplicationMenu> findAll(Pageable page);

  ApplicationMenu findByLabel(String label);

  List<ApplicationMenu> findByRole(Role role);

  List<ApplicationMenu> findApplicationMenuByActiveAndRole(boolean active, Role role);

}
