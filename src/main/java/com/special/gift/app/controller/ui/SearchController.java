package com.special.gift.app.controller.ui;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;
import com.special.gift.app.service.ListingService;
import com.special.gift.app.service.PackageCityService;
import com.special.gift.app.service.VendorDescService;

@Controller
@RequestMapping(value = SearchController.PATH)
public class SearchController {

  private static final Logger log = LoggerFactory.getLogger(SearchController.class);

  public static final String PATH = "/";

  public static final int PAGING_NUMBER = 5;

  public static final String SEARCH = "search";

  public static final String ADVANCE_SEARCH = "advance-search";

  @Inject
  private VendorDescService vendorDescService;

  @Inject
  private ListingService listingService;

  @Inject
  private PackageCityService packageCityService;

  /**
   *
   * mapping for search page
   *
   * @param model
   * @param session
   * @return
   */
  @RequestMapping(value = SEARCH, method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
  public String renderSearchPage(Model model,
      @RequestParam(value = "f", required = false) String keyword, HttpServletRequest request,
      @RequestParam(value = "start", defaultValue = "0", required = false) int start,
      @RequestParam(value = "limit", defaultValue = "12", required = false) int limit) {
    try {
      log.debug("keyword : {}", keyword);
      log.debug("start : {}", start);
      log.debug("limit : {}", limit);

      final FilterDto filter = new FilterDto();
      filter.setKeyword(keyword);
      final List<ItemListDto> itemList = listingService.findAllList(request, null);

      final List<ItemListDto> filteredItemList = new ArrayList<>();

      final List<ItemListDto> displayedItemList = new ArrayList<>();


      // filtering one more time because there is only one parameter
      if (keyword != null && !keyword.isEmpty()) {

        for (int i = 0; i < itemList.size(); i++) {

          final boolean suitableLocation =
              itemList.get(i).getLocation().toLowerCase().contains(keyword.toLowerCase());

          final boolean suitableVendorStyle =
              itemList.get(i).getVendorStyle().toLowerCase().contains(keyword.toLowerCase());

          final boolean suitableName =
              itemList.get(i).getName().toLowerCase().contains(keyword.toLowerCase());

          if (suitableLocation || suitableVendorStyle || suitableName) {
            filteredItemList.add(itemList.get(i));
          }

          displayedItemList.clear();
          displayedItemList.addAll(filteredItemList);
        }


      } else {
        displayedItemList.clear();
        displayedItemList.addAll(itemList);
      }

      log.debug("start : {}", start);

      model.addAttribute("cities", packageCityService.findAll());
      model.addAttribute("events", vendorDescService.findAll().getContent());
      model.addAttribute("totalPage",
          new Double(Math.ceil((double) itemList.size() / (double) limit)).intValue());
      model.addAttribute("totalDisplayItem", start > limit ? start - limit : limit);
      model.addAttribute("pagingNumber", PAGING_NUMBER);



      model.addAttribute("itemList",
          displayedItemList.subList(
              displayedItemList.size() > start ? start
                  : displayedItemList.size() > 0 ? displayedItemList.size() - 1 : 0,
              displayedItemList.size() > limit ? limit : displayedItemList.size()));


      log.debug("item size : {}", itemList.size());


    } catch (final Exception ex) {
      ex.printStackTrace();
    }
    return "/contents/search";
  }


  /**
   *
   * mapping for search page
   *
   * @param model
   * @param session
   * @return
   */
  @RequestMapping(value = SEARCH, method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
  public String renderAdvanceSearchPage(Model model, HttpServletRequest request,
      @RequestParam(value = "start", defaultValue = "0", required = false) int start,
      @RequestParam(value = "limit", defaultValue = "12", required = false) int limit,
      @RequestBody MultiValueMap<String, String> formData) {
    try {

      final String eventType = formData.getFirst("eventType");
      final String city = formData.getFirst("city");
      final String capacity = formData.getFirst("capacity");

      log.debug("event : {}, city : {}, capacity : {}", eventType, city, capacity);

      final FilterDto filter = new FilterDto();

      filter.setCity(city);
      filter.setPackageType(eventType);

      final List<ItemListDto> itemList = listingService.findAllList(request, null, filter);

      log.debug("data found : {}", itemList.size());

      final List<ItemListDto> filteredItemList = new ArrayList<>();

      final List<ItemListDto> displayedItemList = new ArrayList<>();

      final String[] fullCapacity = capacity.split(",");

      log.debug("x {},x {}", fullCapacity[0], fullCapacity[1]);

      for (final ItemListDto item : itemList) {
        if (fullCapacity[0].equals(">")) {
          if (item.getCapacity() > Integer.parseInt(fullCapacity[1])) {
            displayedItemList.add(item);
          }
        } else {
          if (item.getCapacity() < Integer.parseInt(fullCapacity[1])) {
            displayedItemList.add(item);
          }
        }
      }

      log.debug("start : {}", start);

      model.addAttribute("cities", packageCityService.findAll());
      model.addAttribute("events", vendorDescService.findAll().getContent());
      model.addAttribute("totalPage",
          new Double(Math.ceil((double) itemList.size() / (double) limit)).intValue());
      model.addAttribute("totalDisplayItem", start > limit ? start - limit : limit);
      model.addAttribute("pagingNumber", PAGING_NUMBER);



      model.addAttribute("itemList",
          displayedItemList.subList(
              displayedItemList.size() > start ? start
                  : displayedItemList.size() > 0 ? displayedItemList.size() - 1 : 0,
              displayedItemList.size() > limit ? limit : displayedItemList.size()));


      log.debug("item size : {}", itemList.size());


    } catch (final Exception ex) {
      ex.printStackTrace();
    }
    return "/contents/search";
  }


}
