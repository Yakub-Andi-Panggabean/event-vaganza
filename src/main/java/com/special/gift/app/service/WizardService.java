package com.special.gift.app.service;

import java.util.List;

import com.special.gift.app.domain.WizardStepMaster;
import com.special.gift.app.domain.WizardValue;
import com.special.gift.app.dto.CustomLocationDto;
import com.special.gift.app.dto.WizardRequestDto;
import com.special.gift.app.dto.WizardStateDto;

public interface WizardService {


  WizardStateDto saveWizardState(WizardRequestDto dto) throws Exception;

  List<WizardStepMaster> findWizardSteps() throws Exception;

  WizardStepMaster findStepByOrder(int order) throws Exception;

  String findTransactionId(String userid) throws Exception;

  int findWizardLastOrder() throws Exception;

  WizardStepMaster findStepById(int id) throws Exception;

  WizardStepMaster findStepByCategory(String category) throws Exception;

  WizardValue findWizardValue(String transactionId) throws Exception;

  void updateCompleteWizardTransaction(String transactionId) throws Exception;

  void updateCustomLocationWizardValue(CustomLocationDto customLocationDto) throws Exception;

  void updateWizardPreview(String previewedId) throws Exception;

}
