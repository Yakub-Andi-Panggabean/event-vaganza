package com.special.gift.app.controller.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.special.gift.app.domain.WizardStepMaster;
import com.special.gift.app.domain.WizardValue;
import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;
import com.special.gift.app.service.ListingService;
import com.special.gift.app.service.WizardService;
import com.special.gift.app.util.CommonUtil;

@Controller
@RequestMapping(value = WizardPartialController.BASE_PATH)
public class WizardPartialController {

  private static final Logger log = LoggerFactory.getLogger(WizardPartialController.class);

  public static final String BASE_PATH = "/partial/wizard";

  private static final String WIZARD_STEP_PATH = "/step";
  private static final String WIZARD_DATE_STEP_PATH = "/date-step";
  private static final String WIZARD_PREVIEW_STEP_PATH = "/preview/{transactionId}";
  public static final String WIZARD_CUSTOM_LOCATION = "/custom-location/{transactionId}";


  private static final String SKIPPED_KEYWORD = "skipped";

  @Inject
  private ListingService listingItemService;

  @Inject
  private WizardService wizardService;

  @Value("${image.path.location}")
  private String imagePath;



  @GetMapping(value = WIZARD_STEP_PATH, produces = MediaType.TEXT_HTML_VALUE)
  public String renderStep(Model model, @RequestParam(value = "category") String category,
      javax.servlet.http.HttpServletRequest request) {



    final List<ItemListDto> items = new ArrayList<>();


    final FilterDto filter = new FilterDto();
    filter.setCategory(category);

    int currentStep = 0;
    int previousStep = 0;
    int nextStep = 0;

    String stepTitle = "";
    String categoryCriteria = category;


    try {

      if (category != null && category.substring(0, 2).equals("00"))
        categoryCriteria = "000";


      log.debug("category  {}", category);

      final WizardStepMaster wizardStepMaster = wizardService.findStepByCategory(categoryCriteria);

      stepTitle = wizardStepMaster.getStepName().toLowerCase();

      currentStep = wizardStepMaster.getStepOrder();
      previousStep = wizardStepMaster.getStepOrder() - 1;
      nextStep = wizardStepMaster.getStepOrder() + 1;


      filter.setCategory(category);


      items.addAll(listingItemService.findAllList(request, null, filter));


      log.debug("previous step : {}", previousStep);
      log.debug("items : {}", items.toString());

      model.addAttribute("imageRoot", imagePath);
      model.addAttribute("items", items);
      model.addAttribute("stepTitle", stepTitle);
      model.addAttribute("currentStep", currentStep);
      model.addAttribute("previousStep", previousStep);
      model.addAttribute("nextStep", nextStep);


    } catch (final Exception ex) {
      ex.printStackTrace();
    }



    return "partial/wizard-step-content";

  }

  @GetMapping(value = WIZARD_DATE_STEP_PATH, produces = MediaType.TEXT_HTML_VALUE)
  public String renderDateStep() {
    return "partial/wizard-date-step";
  }


  @GetMapping(value = WIZARD_PREVIEW_STEP_PATH, produces = MediaType.TEXT_HTML_VALUE)
  public String renderPlanPreview(Model model,
      @PathVariable(name = "transactionId") String transactionId, HttpServletRequest request) {

    log.debug("transaction id : {}", transactionId);

    FilterDto filterDto = null;

    try {

      final WizardValue wizardValue = wizardService.findWizardValue(transactionId);

      final String stepValues[] = wizardValue.getContent().split(",");

      String eventDate = "";

      final List<Map<String, ItemListDto>> items = new LinkedList<>();

      String customLocation = "";

      for (int i = 0; i < stepValues.length; i++) {


        log.debug("id : {}, value : {}", stepValues[i].split("=")[0], stepValues[i].split("=")[1]);

        if (stepValues[i].split("=")[0].equals("1")) {
          eventDate = stepValues[i].split("=")[1];

        } else {

          if (!stepValues[i].split("=")[1].equals(SKIPPED_KEYWORD)) {

            final WizardStepMaster master =
                wizardService.findStepById(Integer.parseInt(stepValues[i].split("=")[0]));

            if (master.getPackageCategory().equals(CommonUtil.VENUE_WIZARD_PACKAGE_CATEGORY)
                && !stepValues[i].split("=")[1].matches("[0-9]+"))
              customLocation = stepValues[i].split("=")[1];



            filterDto = new FilterDto();

            filterDto.setId(stepValues[i].split("=")[1]);
            final List<ItemListDto> list = listingItemService.findAllList(request, null, filterDto);
            final WizardStepMaster stepMaster =
                wizardService.findStepById(Integer.parseInt(stepValues[i].split("=")[0]));


            if (!list.isEmpty()) {

              final Map<String, ItemListDto> contents = new HashMap<>();
              contents.put(stepMaster.getStepName(), list.get(0));
              items.add(contents);

            }


          }

        }



      }


      log.debug("content : {}", items.toString());

      model.addAttribute("customLocation", customLocation);
      model.addAttribute("transactionId", transactionId);
      model.addAttribute("imageRoot", imagePath);
      model.addAttribute("items", items);
      model.addAttribute("eventDate", eventDate);

    } catch (final Exception ex) {
      ex.printStackTrace();
    }

    return "partial/wizard-preview";
  }


  @RequestMapping(value = WIZARD_CUSTOM_LOCATION, method = RequestMethod.GET,
      produces = MediaType.TEXT_HTML_VALUE)
  public String renderEventLocation(Model model,
      @PathVariable(value = "transactionId") String transactionId) {
    model.addAttribute("transactionId", transactionId);
    return "partial/custom-location-section";
  }

}
