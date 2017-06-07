package com.special.gift.app.service.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special.gift.app.domain.PasswordReminder;
import com.special.gift.app.domain.User;
import com.special.gift.app.repository.PasswordReminderRepository;
import com.special.gift.app.repository.UserRepository;
import com.special.gift.app.service.UserService;
import com.special.gift.app.util.CommonUtil;
import com.special.gift.app.util.exception.DataAlreadyExistException;

@Service
@Transactional(readOnly = true)
public class UserServiceBean implements UserService {

  private static final Logger log = LoggerFactory.getLogger(UserServiceBean.class);

  @Autowired
  private UserRepository repository;
  @Autowired
  private PasswordReminderRepository passwordReminderRepository;

  @Autowired
  private ShaPasswordEncoder passwordEncoder;

  @Override
  public boolean checkUserByPrincipal(String principal) throws Exception {
    return repository.findByEmailOrUsername(principal, principal) != null;
  }

  @Override
  public boolean checkUserByCredential(String credential) throws Exception {
    return repository.findByPassword(credential) != null;
  }

  @Override
  public Page<User> findAllUser(long start, long limit) throws Exception {
    return repository.findAll(new PageRequest((int) start, (int) limit));
  }

  @Override
  public User findUserByPrincipal(String principal) throws Exception {
    return repository.findByEmailOrUsername(principal, principal);
  }

  @Override
  @Transactional(readOnly = false)
  public void insertUser(User user) throws Exception {

    log.debug("new user : {}", user);

    CommonUtil.findNullOrEmptyValue(user.getEmail(), "email");
    CommonUtil.isValidEmail(user.getEmail());
    CommonUtil.findNullOrEmptyValue(user.getHandphone(), "handphone");
    CommonUtil.findNullOrEmptyValue(user.getUserAddress(), "address");
    CommonUtil.findNullOrEmptyValue(user.getUserZip(), "zip code");

    final Map<String, String> existExceptionEntity = new HashMap<>();

    if (repository.findByEmailOrUsername(user.getEmail(), user.getEmail()) != null) {
      existExceptionEntity.clear();
      existExceptionEntity.put("email", user.getEmail());
      throw new DataAlreadyExistException(new StringBuilder("user with email ")
          .append(user.getEmail()).append(" already exist").toString(), existExceptionEntity);
    }

    if (repository.findByEmailOrUsername(user.getUsername(), user.getUsername()) != null) {
      existExceptionEntity.clear();
      existExceptionEntity.put("user", user.getUsername());
      throw new DataAlreadyExistException(new StringBuilder("user with username ")
          .append(user.getUsername()).append(" already exist").toString(), existExceptionEntity);
    }

    user.setPassword(passwordEncoder.encodePassword(user.getPassword(), CommonUtil.SALT));
    user.setUserId(CommonUtil.generateFakeId(repository.findAll()));
    user.setStatus('0');
    repository.save(user);
  }

  @Override
  @Transactional(readOnly = false)
  public void updateUser(User user) throws Exception {
    if (repository.exists(user.getUserId())) {
      repository.save(user);
    }

  }

  @Override
  @Transactional(readOnly = false)
  public void deleteUser(String id) throws Exception {
    repository.delete(id);
  }

  @Override
  public User findUserById(String id) throws Exception {
    return repository.findOne(id);
  }

  @Override
  @Transactional(readOnly = false)
  public User activateUser(String token) throws Exception {

    // userid||currentdatetime||uuid
    final String decoded = CommonUtil.decodeToBase64(token);

    log.info("decoded : {}", decoded);

    final StringTokenizer tokenizer = new StringTokenizer(decoded, "||");
    final String id = tokenizer.nextToken();

    log.info("user id : {}", id);



    if (repository.exists(id)) {
      final User user = repository.findOne(id);
      repository.updateUserStatus('1', id);
      return user;
    } else {
      throw new RuntimeException("user not found");
    }


  }

  @Override
  @Transactional(readOnly = false)
  public User findByEmail(String email) throws Exception {
    final User user = repository.findByEmail(email);
    if (user == null) {
      throw new RuntimeException(
          "There is no event vaganza account associated with email " + email);
    } else {
      try {

        final PasswordReminder reminder = new PasswordReminder();
        reminder.setCreatedDate(new Date());
        reminder.setStatus(false);
        reminder.setUserId(user.getUserId());
        passwordReminderRepository.save(reminder);

      } catch (final Exception exception) {
        exception.printStackTrace();
      }

      return user;
    }

  }

  @Override
  @Transactional(readOnly = false)
  public User resetPassword(String token, String newPassword) throws Exception {
    // userid||currentdatetime||uuid
    final String decoded = CommonUtil.decodeToBase64(token);

    log.info("decoded : {}", decoded);

    final StringTokenizer tokenizer = new StringTokenizer(decoded, "||");
    final String id = tokenizer.nextToken();

    log.info("user id : {}", id);

    if (repository.exists(id)) {
      final User user = repository.findOne(id);
      final User afterPasswordChanged = new User();
      BeanUtils.copyProperties(user, afterPasswordChanged);

      afterPasswordChanged
          .setPassword(passwordEncoder.encodePassword(newPassword, CommonUtil.SALT));

      repository.save(afterPasswordChanged);

      return user;
    } else {
      throw new RuntimeException("user not found");
    }

  }

  @Override
  public boolean checkUserByToken(String token) throws Exception {

    final String decoded = CommonUtil.decodeToBase64(token);

    log.info("decoded : {}", decoded);

    final StringTokenizer tokenizer = new StringTokenizer(decoded, "||");
    final String id = tokenizer.nextToken();

    return repository.exists(id);
  }



}


