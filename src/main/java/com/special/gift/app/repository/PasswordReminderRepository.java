package com.special.gift.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.PasswordReminder;

public interface PasswordReminderRepository extends CrudRepository<PasswordReminder, String> {

}
