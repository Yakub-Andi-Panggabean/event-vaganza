package com.special.gift.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.special.gift.app.domain.Role;
import com.special.gift.app.domain.User;

public interface RoleService {

  void addNewRole(Role role);

  void deleteRole(String roleId);

  void updateRole(Role role);

  Page<Role> findAllRole(long start, long limit);

  List<Role> findRoleByUser(User user);
}
