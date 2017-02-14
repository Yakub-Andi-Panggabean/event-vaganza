package com.special.gift.app.controller.ui;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.special.gift.app.domain.User;
import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;
import com.special.gift.app.service.ListingService;
import com.special.gift.app.service.UserService;
import com.special.gift.app.util.CommonUtil;

@Controller
@RequestMapping(value = AjaxfiedController.BASE_PATH)
public class AjaxfiedController {

  private static final Logger log = LoggerFactory.getLogger(AjaxfiedController.class);

  // root path
  public static final String BASE_PATH = "/ajax";

  // plan my event wizard
  public static final String SECOND_WIZARD_STEP = "/wizard-second/{type}";
  public static final String THIRD_WIZARD_STEP = "/wizard-third";
  public static final String FOURTH_WIZARD_STEP = "/wizard-fourth";
  public static final String FIFTH_WIZARD_STEP = "/wizard-fifth";


  @Inject
  private ListingService listingService;

  @Inject
  private UserService userService;


  @RequestMapping(value = SECOND_WIZARD_STEP, method = RequestMethod.GET,
      produces = MediaType.TEXT_HTML_VALUE)
  public String renderWizardPackage(Model model, HttpServletRequest request,
      @PathVariable(value = "type") String type,
      @RequestParam(value = "start", defaultValue = "0", required = false) int start,
      @RequestParam(value = "limit", defaultValue = "12", required = false) int limit) {

    log.debug("partial request for wizard second step");

    try {

      final FilterDto filter = new FilterDto();
      filter.setCategory(type);

      final List<ItemListDto> itemList = listingService.findAllList(request, null, filter);

      log.debug("data found : {}", itemList.size());


      final List<ItemListDto> displayedItemList = new ArrayList<>();

      log.debug("start : {}", start);

      model.addAttribute("totalPage",
          new Double(Math.ceil(itemList.size() / (double) limit)).intValue());
      model.addAttribute("totalDisplayItem", start > limit ? start - limit : limit);
      model.addAttribute("pagingNumber", CommonUtil.PAGING_NUMBER);

      displayedItemList.addAll(itemList);

      model.addAttribute("itemList",
          displayedItemList.subList(
              displayedItemList.size() > start ? start
                  : displayedItemList.size() > 0 ? displayedItemList.size() - 1 : 0,
              displayedItemList.size() > limit ? limit : displayedItemList.size()));


      log.debug("item size : {}", itemList.size());


    } catch (final Exception exception) {

      exception.printStackTrace();
    }

    return "partial/package-section";
  }


  @RequestMapping(value = THIRD_WIZARD_STEP, method = RequestMethod.GET,
      produces = MediaType.TEXT_HTML_VALUE)
  public String renderWizardVenue(Model model, HttpServletRequest request,
      @RequestParam(value = "start", defaultValue = "0", required = false) int start,
      @RequestParam(value = "limit", defaultValue = "12", required = false) int limit) {

    log.debug("partial request for wizard second step");

    try {

      final FilterDto filter = new FilterDto();
      filter.setCategory("000");

      final List<ItemListDto> itemList = listingService.findAllList(request, null, filter);

      log.debug("data found : {}", itemList.size());


      final List<ItemListDto> displayedItemList = new ArrayList<>();

      log.debug("start : {}", start);

      model.addAttribute("totalPage",
          new Double(Math.ceil(itemList.size() / (double) limit)).intValue());
      model.addAttribute("totalDisplayItem", start > limit ? start - limit : limit);
      model.addAttribute("pagingNumber", CommonUtil.PAGING_NUMBER);

      displayedItemList.addAll(itemList);

      model.addAttribute("itemList",
          displayedItemList.subList(
              displayedItemList.size() > start ? start
                  : displayedItemList.size() > 0 ? displayedItemList.size() - 1 : 0,
              displayedItemList.size() > limit ? limit : displayedItemList.size()));


      log.debug("item size : {}", itemList.size());


    } catch (final Exception exception) {
      exception.printStackTrace();
    }

    return "partial/venue-section";
  }


  @RequestMapping(value = FOURTH_WIZARD_STEP, method = RequestMethod.GET,
      produces = MediaType.TEXT_HTML_VALUE)
  public String renderWizardDate() {
    return "partial/date-section";
  }


  @RequestMapping(value = FIFTH_WIZARD_STEP, method = RequestMethod.GET,
      produces = MediaType.TEXT_HTML_VALUE)
  public String renderWizardConfirm(Model model, HttpServletRequest request, HttpSession session,
      @RequestParam(value = "packages") String packages,
      @RequestParam(value = "venue") String venue, @RequestParam(value = "date") String date,
      @RequestParam(value = "capacity") String capacity) {

    log.debug("package : {}, venue : {}, date : {}, capacity : {}", packages, venue, date,
        capacity);

    try {

      final FilterDto filter = new FilterDto();
      filter.setId(venue);

      final ItemListDto packageVenue = listingService.findAllList(request, null, filter).get(0);

      final FilterDto filter2 = new FilterDto();
      filter.setId(packages);

      final ItemListDto packageVendor = listingService.findAllList(request, null, filter2).get(0);

      final User user = userService.findUserByPrincipal((String) session.getAttribute("userEmail"));


      model.addAttribute("venue", packageVenue);
      model.addAttribute("vendor", packageVendor);
      model.addAttribute("packageDate", date);
      model.addAttribute("capacity", capacity);
      model.addAttribute("requester", user);

    } catch (final Exception ex) {
      ex.printStackTrace();
    }


    return "partial/confirm-section";
  }


}
