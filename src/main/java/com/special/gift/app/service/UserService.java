package com.special.gift.app.service;

import org.springframework.data.domain.Page;

import com.special.gift.app.domain.User;

public interface UserService {

  boolean checkUserByPrincipal(String principal);

  boolean checkUserByCredential(String credential);

  Page<User> findAllUser(long start, long limit);

  User findUserByPrincipal(String principal);

  void insertUser(User user);

  void updateUser(User user);

  void deleteUser(String id);
}
