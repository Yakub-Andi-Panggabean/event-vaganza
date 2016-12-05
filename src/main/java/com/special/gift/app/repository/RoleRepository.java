package com.special.gift.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.Role;
import com.special.gift.app.domain.User;

public interface RoleRepository extends CrudRepository<Role, String> {

  Role findByUsers(User user);

  List<Role> findRoleByRoleName(String roleName);

}
