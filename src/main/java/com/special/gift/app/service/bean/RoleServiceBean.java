package com.special.gift.app.service.bean;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special.gift.app.domain.Role;
import com.special.gift.app.domain.User;
import com.special.gift.app.repository.RoleRepository;
import com.special.gift.app.service.RoleService;

@Service
@Transactional(readOnly = true)
public class RoleServiceBean implements RoleService {

  @Inject
  private RoleRepository roleRepository;

  @Override
  @Transactional(readOnly = false)
  public void addNewRole(Role role) {
    roleRepository.save(role);
  }

  @Override
  @Transactional(readOnly = false)
  public void deleteRole(String roleId) {
    roleRepository.delete(roleId);
  }

  @Override
  @Transactional(readOnly = false)
  public void updateRole(Role role) {
    if (roleRepository.exists(role.getId())) {
      roleRepository.save(role);
    }
  }

  @Override
  public Page<Role> findAllRole(long start, long limit) {
    return roleRepository.findAll(new PageRequest((int) start, (int) limit));
  }

  @Override
  public List<Role> findRoleByUser(User user) {
    return roleRepository.findByUsers(user);
  }

}
