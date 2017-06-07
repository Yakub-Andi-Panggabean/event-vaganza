package com.special.gift.app.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.special.gift.app.domain.PackageVenue;
import com.special.gift.app.domain.User;
import com.special.gift.app.domain.VendorDesc;
import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;
import com.special.gift.app.dto.UserDto;
import com.special.gift.app.service.ListingService;
import com.special.gift.app.service.UserService;
import com.special.gift.app.service.VendorDescService;
import com.special.gift.app.service.VenueService;
import com.special.gift.app.util.CommonUtil;
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
  public static final String VENUE_LIST_API = "/venues";
  public static final String FIND_PACKAGE_BY_ID_PATH = "/packages/{packageId}";
  public static final String IMAGE_DISCOVERY_PATH = "/image/path";
  public static final String USER_BY_EMAIL_API = USER_API + "/email/{mail:.+}";

  @Inject
  private UserService userService;

  @Inject
  private VendorDescService vendorDescService;

  @Inject
  private ListingService listingService;

  @Inject
  private VenueService venueService;

  @Value("${image.path.location}")
  private String imagePath;

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

    final List<VendorDesc> parents = new ArrayList<>();
    for (final VendorDesc vendorCategory : vendorDescService.findAll()) {
      if (vendorCategory.getVendorType().length() > 2
          && String.valueOf(vendorCategory.getVendorType().charAt(2)).equals("0")) {
        parents.add(vendorCategory);
      }
    }

    final Page<VendorDesc> page = new PageImpl<>(parents);

    return new GenericMultipleResponse<VendorDesc>(true, "ok", page);
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

  @GetMapping(value = VENUE_LIST_API)
  public GenericMultipleResponse<PackageVenue> findAllVenue() {
    final Page<PackageVenue> venues = venueService.findAll();
    return new GenericMultipleResponse<>(true, "success", venues);
  }



  @GetMapping(value = FIND_PACKAGE_BY_ID_PATH)
  public GenericSingleResponse<ItemListDto> findPackageById(
      @PathVariable(value = "packageId") String id, HttpServletRequest request) {

    ItemListDto items = null;

    try {
      final FilterDto filter = new FilterDto();
      filter.setId(id);
      if (listingService.findAllList(request, null, filter).size() > 0)
        items = listingService.findAllList(request, null, filter).get(0);

    } catch (final Exception ex) {
      ex.printStackTrace();
      return new GenericSingleResponse<ItemListDto>(false, ex.getMessage(), null);

    }

    return new GenericSingleResponse<ItemListDto>(true, "success", items);
  }


  @GetMapping(value = IMAGE_DISCOVERY_PATH)
  public GenericSingleResponse<String> findImagePath() {
    return new GenericSingleResponse<String>(true, "success", imagePath);
  }

  @GetMapping(value = USER_BY_EMAIL_API)
  public GenericSingleResponse<UserDto> findUserByEmail(@PathVariable(name = "mail") String email) {
    final UserDto dto = new UserDto();
    try {
      final String decoded = CommonUtil.decodeToBase64(email);
      final User user = userService.findByEmail(decoded);
      user.setPassword("");
      user.setUserId("");
      user.setPhone("");
      BeanUtils.copyProperties(user, dto);
    } catch (final Exception exception) {
      log.error("error occured with message : {}", exception.getMessage());
      return new GenericSingleResponse<UserDto>(false, exception.getMessage(), null);
    }

    return new GenericSingleResponse<UserDto>(true, "success", dto);
  }



}
