package com.special.gift.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.Role;
import com.special.gift.app.domain.User;

public interface RoleRepository extends CrudRepository<Role, String> {

  List<Role> findByUsers(User user);

  Role findRoleByRoleName(String roleName);

  Page<Role> findAll(Pageable pageable);
}
