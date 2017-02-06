package com.special.gift.app.controller.api;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.special.gift.app.domain.User;
import com.special.gift.app.domain.VendorDesc;
import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;
import com.special.gift.app.dto.UserDto;
import com.special.gift.app.service.ListingService;
import com.special.gift.app.service.UserService;
import com.special.gift.app.service.VendorDescService;
import com.special.gift.app.util.response.GenericMultipleResponse;
import com.special.gift.app.util.response.GenericResponse;
import com.special.gift.app.util.response.GenericSingleResponse;

@RestController
@RequestMapping(value = ApiController.BASE_PATH)
public class ApiController {

  private static final Logger log = LoggerFactory.getLogger(ApiController.class);

  public static final String BASE_PATH = "/api";
  public static final String USER_API = "/user";
  public static final String VENDOR_TYPE_API = "/categories";
  public static final String ITEMS_LIST_API = "/items/{page}/{size}/{type}";
  public static final String SEARCH_ITEM_API = "/search";
  public static final String AUTHENTICATE_STATUS = "/auth/status";

  @Inject
  private UserService userService;

  @Inject
  private VendorDescService vendorDescService;

  @Inject
  private ListingService listingService;

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


  @GetMapping(value = ITEMS_LIST_API)
  public GenericMultipleResponse<ItemListDto> retrieveItemList(
      @PathVariable(value = "page") int page, @PathVariable(value = "size") int size,
      @PathVariable(value = "type") String type, HttpServletRequest request) {

    log.debug("item type : {}", type);
    try {

      final List<ItemListDto> list = listingService.findAllList(request, type);

      int start = page > 0 ? page * size : page;

      if (start > list.size()) {
        start = list.size();
      }

      if (start < 0) {
        start = 0;
      }

      final Page<ItemListDto> contents = new PageImpl<>(
          list.subList(start, (start + size) > list.size() ? list.size() : (start + size)),
          new PageRequest(page, size), list.size());

      return new GenericMultipleResponse<>(true, "success", contents);

    } catch (final Exception ex) {
      ex.printStackTrace();
      return new GenericMultipleResponse<>(false, ex.getMessage(), null);
    }
  }

  @PostMapping(value = ITEMS_LIST_API)
  public GenericMultipleResponse<ItemListDto> retrieveItemSelectionList(
      @PathVariable(value = "page") int page, @PathVariable(value = "size") int size,
      @PathVariable(value = "type") String type, @RequestBody FilterDto filterDto,
      HttpServletRequest request) {

    log.debug("filter properties : {}", filterDto.toString());

    log.debug("item type : {}", type);

    try {

      final List<ItemListDto> list = listingService.findAllList(request, type, filterDto);

      int start = page > 0 ? page * size : page;

      if (start > list.size()) {
        start = list.size();
      }

      if (start < 0) {
        start = 0;
      }

      log.debug("start index : {}", start);

      final Page<ItemListDto> contents = new PageImpl<>(
          list.subList(start, (start + size) > list.size() ? list.size() : (start + size)),
          new PageRequest(page, size), list.size());

      return new GenericMultipleResponse<>(true, "success", contents);

    } catch (final Exception ex) {
      ex.printStackTrace();
      return new GenericMultipleResponse<>(false, ex.getMessage(), null);
    }


  }

  @PostMapping(value = AUTHENTICATE_STATUS)
  public GenericSingleResponse<String> authenticateLoginStatus(HttpSession session) {

    log.debug("logged user : {}", session.getAttribute("user"));

    String result = "1";
    if (session.getAttribute("user") == null) {
      result = "0";
    }

    return new GenericSingleResponse<String>(true, "success", result);
  }



}
