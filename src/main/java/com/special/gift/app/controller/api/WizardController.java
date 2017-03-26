package com.special.gift.app.controller.api;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.special.gift.app.domain.WizardStepMaster;
import com.special.gift.app.domain.WizardValue;
import com.special.gift.app.dto.CustomLocationDto;
import com.special.gift.app.dto.WizardRequestDto;
import com.special.gift.app.dto.WizardStateDto;
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
  private static final String FIND_LAST_ORDER = "/lastorder";
  private static final String CHECK_VENUE_STATUS = "/venue-status/{transactionId}";
  private static final String DEFINE_CUSTOM_LOCATION = "/custom-location";
  private static final String UPDATE_CUSTOM_LOCATION = "/custom-location-update";


  @Inject
  private WizardService service;

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

}


