package com.special.gift.app.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special.gift.app.domain.User;
import com.special.gift.app.repository.UserRepository;
import com.special.gift.app.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceBean implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public boolean checkUserByPrincipal(String principal) {
    return (userRepository.findByEmailOrUsername(principal, principal) != null);
  }

  @Override
  public boolean checkUserByCredential(String credential) {
    return userRepository.findByPassword(credential) != null;
  }

  @Override
  public Page<User> findAllUser(long start, long limit) {
    return userRepository.findAll(new PageRequest((int) start, (int) limit));
  }

  @Override
  @Transactional(readOnly = false)
  public void insertUser(User user) {
    userRepository.save(user);

  }

  @Override
  @Transactional(readOnly = false)
  public void updateUser(User user) {
    if (userRepository.exists(user.getId()))
      userRepository.save(user);
  }

  @Override
  @Transactional(readOnly = false)
  public void deleteUser(String id) {
    userRepository.delete(id);
  }

  @Override
  public User findUserByPrincipal(String principal) {
    return userRepository.findByEmailOrUsername(principal, principal);
  }


}
