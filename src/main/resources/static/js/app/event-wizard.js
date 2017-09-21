/**
 * js functionality for wizard
 */

/*
 * print error
 */
function errorhandler(jqXHR, textStatus, errorThrown) {
  console.log(errorThrown);
}

/*
 * show item detail modal
 */
function showItemDetailModal(packageId) {
  findPlanPackageById(packageId).done(function(result) {

    var packageEntity = result.content;

    $("#modal-item-detail").modal();

    var modalHtml = $("#modal-item-detail > .modal-dialog").html();

    $("#modal-item-detail > .modal-dialog").html($('#spinner-progress').html());

    renderImageSlider(packageId).done(function(data) {

      $("#modal-item-detail > .modal-dialog").html(modalHtml);

      $("#modal_detail_item_name").text(packageEntity.name);

      $("#modal_detail_item_title").text(packageEntity.name);

      $("#modal_detail_item_info").text(packageEntity.location.concat(" | ").concat(packageEntity.room));

      $("#modal_detail_item_price").text(packageEntity.paxPrice > 0 ? "Rp. ".concat(packageEntity.paxPrice.format(2)) : "Rp. ".concat(packageEntity.price.format(2)));

      $("#modal_detail_item_price_unit").text(packageEntity.paxPrice > 0 ? "Per Pax" : "");

      $("#modal_detail_item_capacity").text(Number(packageEntity.capacity) > 0 ? packageEntity.capacity : packageEntity.vendorStyle);

      $("#modal_detail_item_capacity_unit").text(packageEntity.capacity > 0 ? "Person" : "Style");

      $("#modal_detail_item_duration").text(packageEntity.rentDuration.concat(" Hours"));

      $("#modal_detail_item_duration_unit").text(packageEntity.packageType === "vendor" ? "Duration" : "Room Usage");

      $("#modal_detail_item_description").text(packageEntity.description);

      $("#modal_detail_item_name").html(packageEntity.data);

      $("#modal_detail_item_image").html(data);

      $("#chose_item_detail_button").unbind("click");

      $("#chose_item_detail_button").click(function(data) {

        $("#plan_value_container").val(packageEntity.id);

        $("#button_plan_event_next").attr("disabled", false);

        $("#plan-quote-info-selected").text("Wonderful, ".concat(packageEntity.name).concat(" is a nice choice"));

      });

    });

  });

}

function removePreviewedItem(previewedItemId) {
  return $.ajax({
    url: servletContext + 'api/wizard/remove-preview/' + previewedItemId,
    method: 'POST',
    contentType: "application/json; charset=utf-8",
    dataType: "json"
  });
}

/*
 * find package using package id
 * 
 * @param id package id @returns
 */
function findPlanPackageById(id) {

  return $.ajax({
    url: servletContext + "api/packages/" + id,
    method: "GET"
  });

}

/**
 * 
 * set wizard parameter
 * 
 * @param city
 * @param guest
 * @returns
 */
function setWizardSession(city,guest) {
  
  return $.ajax({
    url: servletContext + "api/wizard/session/param?city=" + city+"&guest="+guest,
    method: "GET"
  });

}


/*
 * display image slider based on package id
 * 
 * @param packageId @returns
 */
function renderImageSlider(packageId) {
  return $.ajax({
    url: servletContext + "ajax/images/slider/" + packageId,
    method: "GET"
  });
}

/*
 * obtain image path from server
 * 
 * @returns
 */
function findImagePath() {
  return $.ajax({
    url: servletContext + "image/path",
    method: "GET"
  });
}

/*
 * find step by order
 */
function findStepByOrder(order) {

  return $.ajax({
    url: servletContext + 'api/wizard/step/' + order,
    method: 'GET',
    dataType: "json"
  });

}

/*
 * find venue id by transaction id
 * 
 * @param id @returns
 */
function findPlanVenueIdByTransactionId(id) {

  return $.ajax({
    url: servletContext + "api/wizard/venue/" + id,
    method: "GET"
  });

}

/*
 * obtain transaction id for wizard
 */
function obtainTransactionId(userId) {

  return $.ajax({
    url: servletContext + 'api/wizard/id/' + userId,
    method: 'GET',
    dataType: "json"
  });

}

/*
 * find last order of wizard
 * 
 * @param currentOrder @returns
 */
function findLastOrder(data) {

  var deffered = new $.Deferred();

  var ajaxResult = $.ajax({
    url: servletContext + 'api/wizard/lastorder',
    method: 'GET'
  });

  deffered.resolve({
    "ajaxResult": ajaxResult,
    "currentPageInfo": data
  });

  return deffered.promise();

}

/*
 * render html content of wizard step
 * 
 * @returns html
 */
function renderStepContent(category) {

  return $.ajax({
    url: servletContext + "partial/wizard/step?category=" + category,
    method: "GET"
  });
}

/*
 * render html content of wizard step
 * 
 * @returns html
 */
function renderStepContentByVenue(category, venueId) {

  return $.ajax({
    url: servletContext + "partial/wizard/step?category=" + category + "&venueId=" + venueId,
    method: "GET"
  });
}

/*
 * find venue status
 * 
 * @returns json
 */
function findVenueStatus(transactionId) {

  return $.ajax({
    url: servletContext + "api/wizard/venue-status/" + transactionId,
    method: "GET"
  });
}

/*
 * render date time step
 * 
 * @returns html
 */
function renderDateStepContent() {

  return $.ajax({
    url: servletContext + "partial/wizard/date-step",
    method: "GET"
  });
}

/*
 * render date time step
 * 
 * @returns html
 */
function renderCustomLocationContent(transactionId) {

  return $.ajax({
    url: servletContext + "partial/wizard/custom-location/" + transactionId,
    method: "GET"
  });
}

/*
 * process wizard
 * 
 * @param params @returns
 */
function processWizardRequest(params) {

  return $.ajax({
    url: servletContext + 'api/wizard/process',
    method: 'POST',
    data: JSON.stringify(params),
    contentType: "application/json; charset=utf-8",
    dataType: "json"
  });

}

function completeWizardRequest(transactionId) {

  return $.ajax({
    url: servletContext + "api/wizard/complete/" + transactionId,
    method: 'POST',
    contentType: "application/json; charset=utf-8"
  });

}

function updateCustomLocation(transactionId, customLocation) {

  var data = {

    "transactionId": transactionId,
    "customLocation": customLocation
  };

  return $.ajax({
    url: servletContext + "api/wizard/custom-location-update",
    method: 'POST',
    data: JSON.stringify(data),
    contentType: "application/json; charset=utf-8"
  });

}

/**
 * render preview page
 * 
 * @param transactionId
 * @returns
 */
function renderPreviewPage(transactionId) {
  return $.ajax({
    url: servletContext + "partial/wizard/preview/" + transactionId,
    method: "GET"
  });
}


/**
 * find city data
 * 
 * @param transactionId
 * @returns
 */
function findExistingCity() {
  return $.ajax({
    url: servletContext + "api/wizard/city",
    method: "GET"
  });
}


/**
 * render preview page
 * 
 * @param transactionId
 * @returns
 */
function processPlanPackageQuantity(transactionId) {
  return $.ajax({
    url: servletContext + "api/wizard/step-id/" + transactionId,
    method: "GET"
  });
}

function executeStepByOrder(transactionId, currentStep, value, skipped, isNext) {

  var dfd = new $.Deferred();

  findStepByOrder(currentStep).done(function(currentStepData) {

    var val = currentStepData.content.id + "=" + value;

    if (skipped) {
      val = currentStepData.content.id + "=skipped";
    }

    dfd.resolve({
      "step": currentStepData.content.id,
      "userId": $("#plan_user_id").val(),
      "transactionId": transactionId,
      "value": val,
      "isComplete": false,
      "isToNext": isNext
    });

  });

  return dfd.promise();

}

/*
 * 
 * update view when change step action triggered
 * 
 */
function changeWizardStep(currentPageInfo, lastStepPromise, isNext) {

  var nextStep = currentPageInfo.content.nextStep;
  var prevStep = currentPageInfo.content.previousStep;
  var currStep = currentPageInfo.content.currentStep;

  var dfr = new $.Deferred();

  var pointer = 0;

  // if go to next step
  if (isNext) {

    pointer = nextStep;

    $("#" + currStep).removeClass();

    $("#" + currStep).removeClass("onprogress");

    $("#" + currStep).addClass("completed");

    $("#" + nextStep).addClass("onprogress");

  } else {// go to previous step

    pointer = prevStep;

    $("#" + currStep).removeClass();

    $("#" + prevStep).addClass("onprogress");

  }

  lastStepPromise.done(function(lastStepInfo) {

    dfr.resolve({
      "destinationStep": pointer,
      "isLastStep": pointer > lastStepInfo.content
    });

  });

  return dfr.promise();

}

/*
 * 
 * call when last wizard step is reached
 * 
 */
function postWizardStep(transactionId) {

  $("#button_plan_event_next").text("Create Event");

  findVenueStatus(transactionId).done(function(result) {

    if (result.content === "without") {

      renderCustomLocationContent(transactionId).done(function(data) {
        $("#wizard_content").html(data);
      });

    } else {

      renderPreviewPage(transactionId).done(function(result) {

        $("#wizard_content").html("");
        $("#wizard_content").html(result);
        $("#wizard_content").hide().fadeIn(2000);

      });

    }

  });
}

/**
 * proceed wizard save data to database
 * 
 * @param step
 *          wizards step : wizard step order
 * @param value
 *          item value : user input value of each step
 * @param complete
 *          complete status : does wizard already complete or not
 * @param isVenue
 *          venue or vendor if false means is vendor else is venue
 * @returns
 */
function proceedWizard(currentStep, value, isNext, skipped) {

  var userId = $("#plan_user_id").val();
  var transactionId = "";

  obtainTransactionId(userId).then(function(result) {

    transactionId = result.message;

    return executeStepByOrder(transactionId, currentStep, value, skipped, isNext);

  }, function(jqXHR, textStatus, errorThrown) {

    console.log(errorThrown);

  }).then(function(currentStepInfo) {

    return processWizardRequest(currentStepInfo);

  }, function(jqXHR, textStatus, errorThrown) {

    console.log(errorThrown);

  }).then(function(data) {

    return findLastOrder(data);

  }, function(jqXHR, textStatus, errorThrown) {

    console.log(errorThrown);

  }).then(function(data) {

    var currentPageInfo = data.currentPageInfo;
    var lastStepPromise = data.ajaxResult;

    return changeWizardStep(currentPageInfo, lastStepPromise, isNext);

  }, function(jqXHR, textStatus, errorThrown) {

    console.log(errorThrown);

  }).then(function(data) {

    var isLastStep = data.isLastStep;
    var destinationStep = data.destinationStep;

    // execute last step
    if (isLastStep) {

      postWizardStep(transactionId);

    } else {

      findStepByOrder(destinationStep).done(function(nextPointerData) {

        // if date step
        if (nextPointerData.content.packageCategory === "---") {

          renderDateStepContent().done(function(dateTimeContent) {

            $("#wizard_content").html(dateTimeContent);
            renderWizardCity();

          });

        } else if (nextPointerData.content.packageCategory === "000") {

          renderStepContent($("#plan_venue_category").val()).done(function(stepContent) {

            $("#wizard_content").html(stepContent);

          });

        } else {

          findPlanVenueIdByTransactionId(transactionId).done(function(data) {

            if (data.content === 'skipped') {

              // render by category if there is no venue
              renderStepContent(nextPointerData.content.packageCategory).done(function(stepContent) {

                $("#wizard_content").html(stepContent);

              });

            } else {

              // render by category and venue id
              renderStepContentByVenue(nextPointerData.content.packageCategory, data.content).done(function(stepContent) {

                $("#wizard_content").html(stepContent);

              });

            }

          });

        }

      });

    }

  }, function(jqXHR, textStatus, errorThrown) {

    console.log(errorThrown);

  });

}

/**
 * entry point of wizard step
 * 
 * @param order
 *          order of the wizard
 * @param isVenue
 *          is step for venue or not
 * @returns
 */
function processWizardTransaction(currentOrder, isNext) {

  var condition = currentOrder == 1 && ($("#wizard-date").val() == null || $("#wizard-date").val() === "");
  
  if(currentOrder===1){
    //set session here
    
    var wizardCity=$("#wizard-city").val();
    var wizardGuest=$("#wizard-guest").val();
    setWizardSession(wizardCity,wizardGuest);
    
  }

  if (condition) {

    swal('Oops...', 'you have to define event date first!', 'error');

  } else {

    $("#plan-quote-info-selected").text("");

    var loading = $('#spinner-progress').html();

    $("#wizard_content").html(loading);

    var value = $("#plan_value_container").val();

    proceedWizard(currentOrder, value, isNext, false);

  }
}

/**
 * skip wizard step
 * 
 * @param order
 *          order of the wizard
 * @param isVenue
 *          is step for venue or not
 * @returns
 */
function skipWizardTransaction(currentOrder, isNext) {

  $("#plan-quote-info-selected").text("");

  var loading = $('#spinner-progress').html();

  $("#wizard_content").html(loading);

  var value = $("#plan_value_container").val();

  proceedWizard(currentOrder, value, isNext, true);

}

/**
 * update the value of custom event venue location if venue is not chosen
 * 
 * @param transactionId
 * @returns
 */
function defineCustomLocation(transactionId) {

  updateCustomLocation(transactionId, $("#custom-event-location").val()).done(function(data) {

    console.log(data);

    renderPreviewPage(data.content).done(function(result) {

      $("#wizard_content").html("");
      $("#wizard_content").html(result);
      $("#wizard_content").hide().fadeIn(2000);

    });

  });

}

/**
 * finalize wizard transaction execute on priewview page
 * 
 * @param transactionId
 * @returns
 */
function finalizeWizardStep(transactionId) {
  // "plan_event_transaction_quantity"
  var quantity = "";

  console.log("transaction id:" + transactionId);
  processPlanPackageQuantity(transactionId).then(function(data) {

    $.each(data, function(index, value) {
      quantity = quantity.concat(value).concat(":").concat($("#pkg-" + value).val()).concat(",");
    });

    console.log("quantity : " + quantity);
    quantity = quantity.slice(0, -1)
    $("#plan_event_transaction_quantity").val(quantity);
    completeWizardRequest(transactionId).done(function(data) {
      $("#plan_event_transaction_form").submit();
    });
  }, function(jqXHR, textStatus, errorThrown) {
    console.log(errorThrown);
  });

}

function removePreview(index, transactionId) {

  var itemId = transactionId + "-preview-" + index;

  swal({
    title: "Are you sure?",
    text: "You will not be able to recover deleted item",
    type: "warning",
    showCancelButton: true,
    confirmButtonColor: '#DD6B55',
    confirmButtonText: 'Yes, I am sure!',
    cancelButtonText: "No, cancel it!",
    closeOnConfirm: false,
    closeOnCancel: false
  }).then(function() {
    console.log("item id :"+itemId);
    updatePlanPackageData(itemId);
    
  }, function() {
    return false;
  });

}



function updatePlanPackageData(itemId){
  
  removePreviewedItem(itemId).then(function(data){
    
    console.log(data);
    if(data.message==='success'){
      $("#"+itemId).remove();
    }
   
  },function(jqXHR, textStatus, errorThrown){
    console.log(errorThrown);
  });
  
}

function renderWizardCity(){
  findExistingCity().then(function(data){
    
    var options="<option></option>";
    
    $.each(data,function(index,value){
      //console.log(value);
      options=options.concat("<option value='"+value.name+"'>").concat(value.description).concat("</option>");
    });
    
    $("#wizard-city").html(options);
    
  },function(jqXHR, textStatus, errorThrown){
    console.log(errorThrown);
  });
}

