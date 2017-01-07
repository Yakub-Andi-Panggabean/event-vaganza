package com.special.gift.app.controller.api;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.special.gift.app.domain.User;
import com.special.gift.app.dto.UserDto;
import com.special.gift.app.service.UserService;
import com.special.gift.app.util.response.GenericResponse;

@RestController(value = "userApiController")
@RequestMapping(value = UserController.BASE_PATH)
public class UserController {

  public static final String BASE_PATH = "/api/user";

  @Inject
  private UserService userService;

  @PostMapping
  public GenericResponse addNewuser(@RequestBody UserDto dto) {
    final User user = new User();
    BeanUtils.copyProperties(dto, user);
    try {
      userService.insertUser(user);
    } catch (final Exception exception) {
      return new GenericResponse(false, exception.getMessage());
    }
    return new GenericResponse(true, user.getEmail());
  }



}
