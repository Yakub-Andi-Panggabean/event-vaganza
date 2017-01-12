package com.special.gift.app.controller.api;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.special.gift.app.domain.User;
import com.special.gift.app.domain.VendorDesc;
import com.special.gift.app.dto.UserDto;
import com.special.gift.app.service.UserService;
import com.special.gift.app.service.VendorDescService;
import com.special.gift.app.util.response.GenericMultipleResponse;
import com.special.gift.app.util.response.GenericResponse;

@RestController
@RequestMapping(value = ApiController.BASE_PATH)
public class ApiController {


  public static final String BASE_PATH = "/api";
  public static final String USER_API = "/user";
  public static final String VENDOR_TYPE_API = "/categories";

  @Inject
  private UserService userService;

  @Inject
  private VendorDescService vendorDescService;

  @PostMapping(value = USER_API)
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



  @GetMapping(value = VENDOR_TYPE_API)
  public GenericMultipleResponse<VendorDesc> getAllVendorType() {
    return new GenericMultipleResponse<VendorDesc>(true, "ok", vendorDescService.findAll());
  }



}
