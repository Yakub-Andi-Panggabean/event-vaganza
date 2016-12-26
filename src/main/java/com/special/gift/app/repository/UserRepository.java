package com.special.gift.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.Role;
import com.special.gift.app.domain.User;

public interface UserRepository extends CrudRepository<User, String> {

  Page<User> findByRoles(Role role, Pageable pageable);

  User findByUsername(String principal);

  User findByEmail(String email);

  User findByPassword(String password);

  Page<User> findByActive(boolean active, Pageable pageable);

  Page<User> findAll(Pageable pageable);

  User findByEmailOrUsername(String email, String username);


}
