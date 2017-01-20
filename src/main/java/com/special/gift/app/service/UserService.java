package com.special.gift.app.service;

import org.springframework.data.domain.Page;

import com.special.gift.app.domain.User;

public interface UserService {

  boolean checkUserByPrincipal(String principal) throws Exception;

  boolean checkUserByCredential(String credential) throws Exception;

  Page<User> findAllUser(long start, long limit) throws Exception;

  User findUserByPrincipal(String principal) throws Exception;

  User findUserById(String id) throws Exception;

  void insertUser(User user) throws Exception;

  void updateUser(User user) throws Exception;

  void deleteUser(String id) throws Exception;

}
