package com.special.gift.app.controller.ui;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;
import com.special.gift.app.service.ListingService;
import com.special.gift.app.util.CommonUtil;

@Controller
@RequestMapping(value = CategoryController.PATH)
public class CategoryController {

  private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

  public static final String PATH = "/";

  public static final String CATEGORY = "find-categories";

  public static final String DETAILED_CATEGORY = "advance-find-categories";

  @Inject
  private ListingService listingService;

  @Value("${image.path.location}")
  private String imagePath;

  @RequestMapping(value = CATEGORY, method = RequestMethod.GET,
      produces = MediaType.TEXT_HTML_VALUE)
  public String renderCategoryItems(Model model,
      @RequestParam(value = "c", required = false) String parentCategory,
      @RequestParam(value = "start", defaultValue = "0", required = false) int start,
      @RequestParam(value = "limit", defaultValue = "12", required = false) int limit,
      HttpServletRequest request) {


    try {



      final List<ItemListDto> itemList = listingService.findAllList(request, parentCategory);

      model.addAttribute("totalPage",
          new Double(Math.ceil((double) itemList.size() / (double) limit)).intValue());
      model.addAttribute("totalDisplayItem", start > limit ? start - limit : limit);
      model.addAttribute("pagingNumber", CommonUtil.PAGING_NUMBER);
      model.addAttribute("imagePath", imagePath);
      model.addAttribute("itemList",
          itemList.subList(
              itemList.size() > start ? start : itemList.size() > 0 ? itemList.size() - 1 : 0,
              itemList.size() > limit ? limit : itemList.size()));


      log.debug("item size : {}", itemList.size());


    } catch (final Exception e) {
      e.printStackTrace();
    }

    return "/contents/finder";
  }



  @RequestMapping(value = DETAILED_CATEGORY, method = RequestMethod.POST,
      produces = MediaType.TEXT_HTML_VALUE)
  public String renderAdvanceCategoryItems(Model model,
      @RequestParam(value = "c", required = false) String parentCategory,
      @RequestParam(value = "start", defaultValue = "0", required = false) int start,
      @RequestParam(value = "limit", defaultValue = "12", required = false) int limit,
      @RequestBody FilterDto filterDto, HttpServletRequest request) {


    try {

      log.info("start : {}, limit : {}", start, limit);
      log.info("keyword : {}", filterDto.getKeyword());

      final List<ItemListDto> itemList =
          listingService.findAllList(request, parentCategory, filterDto);



      final List<ItemListDto> after = itemList.subList(
          itemList.size() > start ? start : itemList.size() > 0 ? itemList.size() - 1 : 0,
          itemList.size() > limit ? limit : itemList.size());

      log.info("original list size: {}, after list size : {}", itemList.size(), after.size());


      model.addAttribute("totalPage",
          new Double(Math.ceil((double) itemList.size() / (double) limit)).intValue());
      model.addAttribute("totalDisplayItem", start > limit ? start - limit : limit);
      model.addAttribute("pagingNumber", CommonUtil.PAGING_NUMBER);
      model.addAttribute("imagePath", imagePath);
      model.addAttribute("itemList", after);


    } catch (final Exception ex) {
      ex.printStackTrace();
    }

    return "/contents/finder";
  }

}
