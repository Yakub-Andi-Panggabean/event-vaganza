package com.special.gift.app.service.bean;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special.gift.app.domain.WizardStepMaster;
import com.special.gift.app.domain.WizardTransaction;
import com.special.gift.app.domain.WizardValue;
import com.special.gift.app.dto.CustomLocationDto;
import com.special.gift.app.dto.WizardRequestDto;
import com.special.gift.app.dto.WizardStateDto;
import com.special.gift.app.repository.WizardStepMasterRepository;
import com.special.gift.app.repository.WizardTransactionRepository;
import com.special.gift.app.repository.WizardValueRepository;
import com.special.gift.app.service.SequenceService;
import com.special.gift.app.service.WizardService;
import com.special.gift.app.util.SequenceUtil;

@Service
@Transactional(readOnly = true)
public class WizardServiceBean implements WizardService {

  private static final Logger log = LoggerFactory.getLogger(WizardService.class);


  @Autowired
  private WizardTransactionRepository transactionRepository;

  @Autowired
  private WizardValueRepository valueRepository;

  @Autowired
  private WizardStepMasterRepository masterRepository;

  @Autowired
  private SequenceService sequenceService;

  /**
   *
   * save transaction in every step
   *
   */
  @Override
  @Transactional(readOnly = false)
  public WizardStateDto saveWizardState(WizardRequestDto dto) throws Exception {

    final StringBuilder csvWizardValues = new StringBuilder();

    final WizardTransaction transaction = new WizardTransaction();
    transaction.setStatus(dto.getIsComplete());
    transaction.setTransactionId(dto.getTransactionId());
    transaction.setUserId(dto.getUserId());

    final WizardValue existingwizardValue = valueRepository.findOne(dto.getTransactionId());


    boolean containedValue = false;


    if (existingwizardValue != null && existingwizardValue.getContent() != null) {

      final List<String> val = Arrays.asList(existingwizardValue.getContent().split(","));


      if (val.size() > 0) {

        for (int i = 0; i < val.size(); i++) {

          if (val.get(i).split("=")[0].equals(dto.getValue().split("=")[0])) {
            val.set(i, dto.getValue());
            containedValue = true;
          }

          csvWizardValues.append(val.get(i)).append(",");

        }

      }

    }



    if (!containedValue) {
      csvWizardValues.append(dto.getValue());
    }



    final WizardValue wizardValue = new WizardValue();
    wizardValue.setContent(csvWizardValues.toString());
    wizardValue.setCurrentStepId(dto.getStep());
    wizardValue.setTransactionId(dto.getTransactionId());

    if (dto.getIsToNext()) {
      valueRepository.save(wizardValue);
      transactionRepository.save(transaction);
    }


    return new WizardStateDto(dto.getTransactionId(), dto.getStep() - 1 > 0 ? dto.getStep() - 1 : 1,
        dto.getStep(), dto.getStep() + 1);

  }

  /**
   *
   * retrieve all wizard step
   *
   */
  @Override
  public List<WizardStepMaster> findWizardSteps() {
    final List<WizardStepMaster> steps = masterRepository.findAllSteps();

    return steps;
  }

  /**
   *
   * find wizard transaction id
   *
   */
  @Override
  public String findTransactionId(String userId) {

    final String existingTransactionId = transactionRepository.findUnfinishedTransaction(userId);

    log.debug("unfinished transaction id : {}", existingTransactionId);

    return existingTransactionId != null && !existingTransactionId.equals("")
        ? existingTransactionId
        : sequenceService.generateSequence(SequenceUtil.WIZARD_TRANSACTION_ID);
  }

  @Override
  public WizardStepMaster findStepByOrder(int order) throws Exception {
    try {
      return masterRepository.findByOrder(order);
    } catch (final Exception exception) {
      exception.printStackTrace();
      throw exception;
    }
  }

  /**
   *
   * find last order of wizard
   *
   */
  @Override
  public int findWizardLastOrder() throws Exception {

    int result = 0;

    for (final WizardStepMaster master : masterRepository.findAll()) {

      if (master.getStepOrder() > result)
        result = master.getStepOrder();

    }

    log.info("last order : {}", result);
    return result;
  }

  @Override
  public WizardStepMaster findStepById(int id) {
    return masterRepository.findOne(id);
  }

  @Override
  public WizardStepMaster findStepByCategory(String category) throws Exception {
    return masterRepository.findByPackageCategory(category);
  }

  @Override
  public WizardValue findWizardValue(String transactionId) throws Exception {

    return valueRepository.findOne(transactionId);
  }

  @Override
  @Transactional(readOnly = false)
  public void updateCompleteWizardTransaction(String transactionId) throws Exception {

    log.debug("completed transaction : {}", transactionId);

    final WizardTransaction transaction = transactionRepository.findOne(transactionId);

    if (transaction != null) {
      final WizardTransaction trans = new WizardTransaction();
      trans.setStatus(true);
      trans.setTransactionId(transactionId);
      trans.setUserId(transaction.getUserId());
      transactionRepository.save(trans);
    } else {
      throw new Exception("wizard transaction id is not found");
    }

  }

  @Override
  @Transactional(readOnly = false)
  public void updateCustomLocationWizardValue(CustomLocationDto customLocation) throws Exception {

    final StringBuilder result = new StringBuilder();

    try {
      final WizardValue wizardValue = valueRepository.findOne(customLocation.getTransactionId());

      if (wizardValue != null && wizardValue.getContent() != null
          && !wizardValue.getContent().equals("")) {


        final String value[] = wizardValue.getContent().split(",");

        for (int i = 0; i < value.length; i++) {

          if (value[i].split("=")[0].equals("2") && value[i].split("=")[1].equals("skipped")) {

            value[i] = "2=".concat(
                customLocation.getCustomLocation().replaceAll(",", ".").replaceAll("=", ""));

          }

          result.append(value[i]).append(",");

        }

        final WizardValue updatedValue = new WizardValue();
        updatedValue.setContent(result.toString());
        updatedValue.setCurrentStepId(findWizardLastOrder());
        updatedValue.setTransactionId(wizardValue.getTransactionId());

        valueRepository.save(updatedValue);

      } else {
        throw new RuntimeException(
            "cannot found value with transaction id : ".concat(customLocation.getTransactionId()));
      }

    } catch (final Exception exception) {
      exception.printStackTrace();
      throw new RuntimeException(exception.getMessage());
    }
  }


  @Override
  @Transactional(readOnly = false)
  public void updateWizardPreview(String previewItem) {

    log.info("=================== preview item : {}", previewItem);

    final String wizardValueId = previewItem.split("-")[0];
    final String wizardValueOrder = previewItem.split("-")[2];


    final WizardValue value = valueRepository.findOne(wizardValueId);
    final StringBuilder newContent = new StringBuilder();

    final String contents[] = value.getContent().split(",");

    for (String content : contents) {


      if (wizardValueOrder.equals(content.split("=")[0])) {
        content = wizardValueOrder.concat("=skipped");
      }

      newContent.append(content).append(",");

    }


    log.info("new content after removed preview item : {}", newContent.toString());


    if (valueRepository.exists(value.getTransactionId())) {

      log.info("updating value with transaction id : {}", value.getTransactionId());
      value.setContent(newContent.substring(0, newContent.length() - 2));
      valueRepository.save(value);

    }



  }



}
