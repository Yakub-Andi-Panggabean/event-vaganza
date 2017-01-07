package com.special.gift.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.special.gift.app.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  Page<User> findAll(Pageable pageable);

  User findByEmailOrUsername(String email, String username);

  User findByPassword(String password);

  User findByStatus(char status);

}
