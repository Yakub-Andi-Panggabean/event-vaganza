package com.special.gift.app.controller.ui;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
  public static final String WIZARD_STEP = "/wizard-package/{type}";
  public static final String WIZARD_STEP_VENUE = "/wizard-package/{venue}/{type}";
  public static final String WIZARD_CUSTOM_LOCATION = "/wizard-package/custom-location";

  @Inject
  private ListingService listingService;

  @Inject
  private UserService userService;

  @Value("${image.path.location}")
  private String imagePath;



  @RequestMapping(value = WIZARD_STEP, method = RequestMethod.GET,
      produces = MediaType.TEXT_HTML_VALUE)
  public String renderWizardPackage(Model model, HttpServletRequest request,
      @PathVariable(value = "type") String type,
      @RequestParam(value = "start", defaultValue = "0", required = false) int start,
      @RequestParam(value = "limit", defaultValue = "12", required = false) int limit) {

    try {

      final FilterDto filter = new FilterDto();
      if (type.equalsIgnoreCase("venue")) {
        filter.setPackageType("venue");
      } else {
        filter.setParent(type);
      }
      final List<ItemListDto> itemList = listingService.findAllList(request, null, filter);

      log.debug("data found : {}", itemList.size());


      final List<ItemListDto> displayedItemList = new ArrayList<>();

      log.debug("start : {}", start);

      model.addAttribute("imageRoot", imagePath);
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



  @RequestMapping(value = WIZARD_STEP_VENUE, method = RequestMethod.GET,
      produces = MediaType.TEXT_HTML_VALUE)
  public String renderWizardVenuePackage(Model model, HttpServletRequest request,
      @PathVariable(value = "type") String type, @PathVariable(value = "venue") String venue,
      @RequestParam(value = "start", defaultValue = "0", required = false) int start,
      @RequestParam(value = "limit", defaultValue = "12", required = false) int limit) {

    try {

      final FilterDto filter = new FilterDto();
      if (!type.equalsIgnoreCase("venue")) {

        filter.setVenue(venue);
        filter.setParent(type);

        log.debug("venue > : {}, type > : {} ", venue, type);

        final List<ItemListDto> itemList = listingService.findAllList(request, null, filter);

        // log.debug("data found : {}", itemList.size());


        final List<ItemListDto> displayedItemList = new ArrayList<>();

        log.debug("start : {}", start);

        model.addAttribute("imageRoot", imagePath);
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

      }

    } catch (final Exception exception) {
      exception.printStackTrace();
    }

    return "partial/package-section";
  }

  @RequestMapping(value = WIZARD_CUSTOM_LOCATION, method = RequestMethod.GET,
      produces = MediaType.TEXT_HTML_VALUE)
  public String renderEventLocation() {
    return "partial/custom-location-section";
  }



}
