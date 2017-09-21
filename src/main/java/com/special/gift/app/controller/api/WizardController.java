package com.special.gift.app.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.special.gift.app.domain.City;
import com.special.gift.app.domain.WizardStepMaster;
import com.special.gift.app.domain.WizardValue;
import com.special.gift.app.dto.CustomLocationDto;
import com.special.gift.app.dto.WizardRequestDto;
import com.special.gift.app.dto.WizardStateDto;
import com.special.gift.app.service.PackageCityService;
import com.special.gift.app.service.WizardService;
import com.special.gift.app.util.response.GenericResponse;
import com.special.gift.app.util.response.GenericSingleResponse;

@RestController
@RequestMapping(value = WizardController.BASE_PATH)
public class WizardController {

  private static final Logger log = LoggerFactory.getLogger(WizardController.class);

  public static final String BASE_PATH = "/api/wizard";
  private static final String PROCESS_PATH = "/process";
  private static final String COMPLETE_PATH = "/complete/{transaction}";
  private static final String TRANSACTION_ID_PATH = "/id/{userId}";
  private static final String FIND_STEP_BY_ORDER_PATH = "/step/{order}";
  private static final String FIND_STEP_BY_STEP_ID = "/step/id/{id}";
  private static final String FIND_LAST_ORDER = "/lastorder";
  private static final String CHECK_VENUE_STATUS = "/venue-status/{transactionId}";
  private static final String DEFINE_CUSTOM_LOCATION = "/custom-location";
  private static final String UPDATE_CUSTOM_LOCATION = "/custom-location-update";
  private static final String FIND_WIZARD_VENUE_BY_TRANSACTION_ID = "/venue/{transactionId}";
  private static final String FIND_STEP_IS_LIST_BY_TRANSACTION_ID = "/step-id/{transactionId}";
  private static final String REMOVED_PLAN_PREVIEW_ITEM = "/remove-preview/{itemId}";
  private static final String SET_WIZARD_SESSION_PARAMETER = "/session/param";
  private static final String FIND_WIZARD_CITY = "/city";


  @Inject
  private WizardService service;

  @Inject
  private PackageCityService cityService;

  @PostMapping(value = PROCESS_PATH, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public GenericSingleResponse<WizardStateDto> proceedWizard(
      @RequestBody WizardRequestDto request) {

    try {

      log.debug("wizard process : {}", request);

      final WizardStateDto dto = service.saveWizardState(request);

      log.debug("process wizard state : {}", dto.toString());

      return new GenericSingleResponse<WizardStateDto>(true, "success", dto);
    } catch (final Exception exception) {
      exception.printStackTrace();
      return new GenericSingleResponse<WizardStateDto>(false, exception.getMessage(), null);
    }


  }

  @PostMapping(value = COMPLETE_PATH, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public GenericSingleResponse<String> completeTransaction(
      @PathVariable("transaction") String transactionId) {
    try {
      service.updateCompleteWizardTransaction(transactionId);
      return new GenericSingleResponse<String>(true, "success", transactionId);
    } catch (final Exception e) {
      e.printStackTrace();
      return new GenericSingleResponse<String>(false, e.getMessage(), transactionId);
    }

  }


  @GetMapping(value = TRANSACTION_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericResponse findTransactionId(@PathVariable("userId") String userId) {

    String transaction = "";
    try {

      transaction = service.findTransactionId(userId);
    } catch (final Exception exception) {
      exception.printStackTrace();
    }

    return new GenericResponse(true, transaction);
  }



  @GetMapping(value = FIND_STEP_BY_ORDER_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericSingleResponse<WizardStepMaster> findByOrder(@PathVariable("order") int order) {

    try {
      final WizardStepMaster content = service.findStepByOrder(order);
      return new GenericSingleResponse<WizardStepMaster>(true, "success", content);
    } catch (final Exception ex) {
      ex.printStackTrace();
      return new GenericSingleResponse<WizardStepMaster>(true, ex.getMessage(), null);
    }

  }

  @GetMapping(value = FIND_STEP_BY_STEP_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericSingleResponse<WizardStepMaster> findByStepId(@PathVariable("id") int order) {

    try {
      final WizardStepMaster content = service.findStepByOrder(order);
      return new GenericSingleResponse<WizardStepMaster>(true, "success", content);
    } catch (final Exception ex) {
      ex.printStackTrace();
      return new GenericSingleResponse<WizardStepMaster>(true, ex.getMessage(), null);
    }

  }

  @GetMapping(value = FIND_LAST_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericSingleResponse<Integer> findLastOrder() {

    try {
      return new GenericSingleResponse<Integer>(true, "success", service.findWizardLastOrder());
    } catch (final Exception ex) {
      ex.printStackTrace();
      return new GenericSingleResponse<Integer>(true, ex.getMessage(), 0);
    }

  }

  @GetMapping(value = CHECK_VENUE_STATUS, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericSingleResponse<String> findVenueUsageStatus(
      @PathVariable("transactionId") String transactionId) {

    try {
      final WizardValue wizardValue = service.findWizardValue(transactionId);
      if (wizardValue.getContent() != null && !wizardValue.getContent().equals("")) {
        final String values[] = wizardValue.getContent().split(",");
        for (int i = 0; i < values.length; i++) {

          if (values[i].split("=")[0].equals("2") && values[i].split("=")[1].equals("skipped")) {
            return new GenericSingleResponse<>(true, "success", "without");
          }

        }
      }
    } catch (final Exception ex) {
      return new GenericSingleResponse<>(false, ex.getMessage(), ex.getMessage());
    }

    return new GenericSingleResponse<>(true, "success", "with");


  }


  @PostMapping(value = UPDATE_CUSTOM_LOCATION, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public GenericSingleResponse<String> updateSkippedVenue(
      @RequestBody CustomLocationDto customLocation) {

    log.debug("custom location dto : {}", customLocation.toString());

    try {
      service.updateCustomLocationWizardValue(customLocation);
      return new GenericSingleResponse<String>(true, "success", customLocation.getTransactionId());
    } catch (final Exception ex) {
      return new GenericSingleResponse<String>(true, "failed", ex.getMessage());

    }

  }



  @GetMapping(value = FIND_WIZARD_VENUE_BY_TRANSACTION_ID,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericSingleResponse<String> findVenue(
      @PathVariable("transactionId") String transactionId) {

    String stepOrder = null;
    String stepValue = null;

    try {
      final WizardValue wizardValue = service.findWizardValue(transactionId);
      if (wizardValue.getContent() != null && !wizardValue.getContent().equals("")) {

        final String values[] = wizardValue.getContent().split(",");



        for (int i = 0; i < values.length; i++) {



          stepOrder = values[i].split("=")[0];
          stepValue = values[i].split("=")[1];


          log.info("step order : {}, step value  : {}", stepOrder, stepValue);

          if (stepOrder.equals("2")) {

            break;
          }

        }
      }
    } catch (final Exception ex) {
      return new GenericSingleResponse<>(false, ex.getMessage(), ex.getMessage());
    }

    return new GenericSingleResponse<>(true, "success", stepValue);

  }


  @GetMapping(value = FIND_STEP_IS_LIST_BY_TRANSACTION_ID,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public final List<String> findChosenStepIdList(
      @PathVariable(value = "transactionId") String transactionId) {
    final List<String> list = new ArrayList<>();
    try {

      final String value = service.findWizardValue(transactionId).getContent();

      final String val[] = value.split(",");


      for (int i = 0; i < val.length; i++) {
        if (i > 1 && !val[i].split("=")[1].equals("skipped")) {// skip address{0} and venue{1}
          list.add(val[i].split("=")[1]);
        }

      }

    } catch (final Exception e) {
      e.printStackTrace();
    }
    return list;
  }


  @PostMapping(value = REMOVED_PLAN_PREVIEW_ITEM, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericResponse removedPreviewedItem(@PathVariable("itemId") String previewedId) {

    final GenericResponse response = new GenericResponse();

    try {
      service.updateWizardPreview(previewedId);
      response.setSuccess(true);
      response.setMessage("success");
    } catch (final Exception e) {
      response.setSuccess(false);
      response.setMessage(e.getMessage());
    }

    return response;

  }

  @GetMapping(value = SET_WIZARD_SESSION_PARAMETER, produces = MediaType.APPLICATION_JSON_VALUE)
  public GenericResponse setWizardParameter(@RequestParam("city") String city,
      @RequestParam("guest") String guest, HttpSession session) {

    log.info("city : {} and guest {}", city, guest);

    final GenericResponse response = new GenericResponse();

    session.setAttribute("wizardCity", city);
    session.setAttribute("wizardGuest", guest);

    response.setSuccess(true);
    response.setMessage("success");

    return response;

  }


  @GetMapping(value = FIND_WIZARD_CITY, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<City> findWizardCity() {

    List<City> list = null;

    try {

      list = cityService.findAll();

    } catch (final Exception ex) {
      log.error("{}", ex.getMessage());
    }

    return list;

  }

}


