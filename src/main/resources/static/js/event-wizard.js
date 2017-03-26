/**
 * 
 * js functionality for wizard
 * 
 */


/**
 * 
 * show item detail modal
 * 
 */
function showItemDetailModal(packageId){
	findPlanPackageById(packageId).done(function(result){
		
		var packageEntity=result.content;
		
		renderImageSlider(packageId).done(function(data){
						
			$("#modal_detail_item_name").text(packageEntity.name);
			$("#modal_detail_item_title").text(packageEntity.name);
			
			$("#modal_detail_item_info").text(packageEntity.location.concat(" | ").concat(packageEntity.room));
			
			$("#modal_detail_item_price").text(packageEntity.paxPrice>0?"Rp. ".concat(packageEntity.paxPrice.format(2)):
				"Rp. ".concat(packageEntity.price.format(2)));
			$("#modal_detail_item_price_unit").text(packageEntity.paxPrice>0?"Per Pax":"");
			
			$("#modal_detail_item_capacity").text(Number(packageEntity.capacity)>0?packageEntity.capacity:packageEntity.vendorStyle);
			$("#modal_detail_item_capacity_unit").text(packageEntity.capacity>0?"Person":"Style");
			
			$("#modal_detail_item_duration").text(packageEntity.rentDuration.concat(" Hours"));
			$("#modal_detail_item_duration_unit").text(packageEntity.packageType==="vendor"?"Duration":"Room Usage");
			
			$("#modal_detail_item_description").text(packageEntity.description);
			
			$("#modal_detail_item_name").html(packageEntity.data);
			
			$("#modal_detail_item_image").html(data);
			
			$("#chose_item_detail_button").unbind("click");
			$("#chose_item_detail_button").click(function(data){
				
				$("#plan_value_container").val(packageEntity.id);
				$("#button_plan_event_next").attr("disabled",false);
				$("#plan-quote-info-selected").text("Wonderful, ".concat(packageEntity.name).concat(" is a nice choice"));
				
			});
			
			
			$("#modal-item-detail").modal();
			
		});
		
		
	});
	
}

/**
 * 
 * find package using package id
 * 
 * @param id package id
 * @returns
 */
function findPlanPackageById(id){
	
	return $.ajax({
		url:"/" + servletContext + "/api/packages/" + id,
		method:"GET"
	});
	
}

/**
 * 
 * display image slider based on package id
 * 
 * @param packageId
 * @returns
 */
function renderImageSlider(packageId){
	return $.ajax({
		url:"/" + servletContext + "/ajax/images/slider/"+packageId,
		method:"GET"
	});
}


/**
 * 
 * obtain image path from server
 * 
 * @returns
 */
function findImagePath(){
	return $.ajax({
		url:"/" + servletContext + "/image/path",
		method:"GET"
	});
}


/**
 * 
 * find step by order
 * 
 */
function findStepByOrder(order) {

	return $.ajax({
		url : '/' + servletContext + '/api/wizard/step/' + order,
		method : 'GET',
		dataType : "json"
	});

}

/**
 * 
 * obtain transaction id for wizard
 * 
 */
function obtainTransactionId(userId) {

	return $.ajax({
		url : '/' + servletContext + '/api/wizard/id/' + userId,
		method : 'GET',
		dataType : "json"
	});

}

/**
 * 
 * find last order of wizard
 * 
 * @param currentOrder
 * @returns
 */
function findLastOrder() {

	return $.ajax({
		url : '/' + servletContext + '/api/wizard/lastorder',
		method : 'GET'
	});

}

/**
 * 
 * render html content of wizard step
 * 
 * @returns html
 */
function renderStepContent(category) {
	
	return $.ajax({
		url : "/" + servletContext + "/partial/wizard/step?category="
				+ category,
		method : "GET"
	});
}

/**
 * 
 * find venue status
 * 
 * @returns json
 */
function findVenueStatus(transactionId) {
	
	return $.ajax({
		url : "/" + servletContext + "/api/wizard/venue-status/"+transactionId,
		method : "GET"
	});
}


/**
 * 
 * render date time step
 * 
 * @returns html
 */
function renderDateStepContent() {
	
	return $.ajax({
		url : "/" + servletContext + "/partial/wizard/date-step",
		method : "GET"
	});
}

/**
 * 
 * render date time step
 * 
 * @returns html
 */
function renderCustomLocationContent(transactionId) {
		
	return $.ajax({
		url : "/" + servletContext + "/partial/wizard/custom-location/"+transactionId,
		method : "GET"
	});
}



/**
 * 
 * process wizard
 * 
 * @param params
 * @returns
 */
function processWizardRequest(params) {

	return $.ajax({
		url : '/' + servletContext + '/api/wizard/process',
		method : 'POST',
		data : JSON.stringify(params),
		contentType : "application/json; charset=utf-8",
		dataType : "json"
	});

}

function completeWizardRequest(transactionId) {

	return $.ajax({
		url : "/" + servletContext + "/api/wizard/complete/"+transactionId,
		method : 'POST',
		contentType : "application/json; charset=utf-8"
	});

}



function updateCustomLocation(transactionId,customLocation) {

	var data={
			
			"transactionId":transactionId,
			"customLocation":customLocation
	};
	
	return $.ajax({
		url : "/" + servletContext + "/api/wizard/custom-location-update",
		method : 'POST',
		data : JSON.stringify(data),
		contentType : "application/json; charset=utf-8"
	});

}




/**
 * 
 * render preview page
 * 
 * @param transactionId
 * @returns
 */
function renderPreviewPage(transactionId){
	return $.ajax({
		url : "/" + servletContext + "/partial/wizard/preview/"+transactionId,
		method : "GET"
	});
}

/**
 * 
 * proceed wizard save data to database
 * 
 * @param step
 *            wizards step : wizard step order
 * @param value
 *            item value : user input value of each step
 * @param complete
 *            complete status : does wizard already complete or not
 * 
 * @param isVenue
 *            venue or vendor if false means is vendor else is venue
 * 
 * @returns
 */
function proceedWizard(currentStep, value ,isNext,skipped) {
	
	console.log("is move to next : "+isNext);

	var userId = $("#plan_user_id").val();

	obtainTransactionId(userId).done(function(result) {
		
		 var transactionId = result.message;
		
		findStepByOrder(currentStep).done(function(currentStepData) {
			
			  var val = currentStepData.content.id + "=" + value;
			  
			  if(skipped){
				  val = currentStepData.content.id + "=skipped";
			  }
			  
			  var params = {
						"step" : currentStepData.content.id,
						"userId" : userId,
						"transactionId" : transactionId,
						"value" : val,
						"isComplete" : false,
						"isToNext" : isNext
		      }
			  
			processWizardRequest(params).done( function(data) {
				
				console.log(data);
				
				var nextStep = data.content.nextStep;
				var prevStep=data.content.previousStep;
				var currStep=data.content.currentStep;
				
				findLastOrder().done(function(orderResult) {
				
					var pointer=0;
					
					if(isNext){
						pointer=nextStep;
						$("#"+ currStep).removeClass();
						$("#"+ currStep).removeClass("onprogress");
						$("#"+ currStep).addClass("completed");
						$("#"+ nextStep).addClass("onprogress");
					
					}else{
						pointer=prevStep;
						$("#"+ currStep).removeClass();
						$("#"+ prevStep).addClass("onprogress");
						
					}
					
					
					
					if (pointer <= orderResult.content) {
						
						
						findStepByOrder(pointer).done(function(nextPointerData) {
							
							
							if(nextPointerData.content.packageCategory === "---"){
								
								renderDateStepContent().done(function(dateTimeContent){
									
									$("#wizard_content").html(dateTimeContent);
									
								});
								
								
							}else{
								
								
								var category= nextPointerData.content.packageCategory==="000" ?
										$("#plan_venue_category").val():
											nextPointerData.content.packageCategory;
										
									renderStepContent(category).done(function(stepContent) {
														
											$("#wizard_content").html(stepContent);
							
									});
								
								
							}
							
							
						});
						
						
					}else{
						
						
						//step is complete
						$("#button_plan_event_next").text("Create Event");
						
						//completeWizardRequest(transactionId).done(function(data){
							
							
							findVenueStatus(transactionId).done(function(result){
								
								console.log(result);
								
								if(result.content==="without"){
																		
									renderCustomLocationContent(transactionId).done(function(data){
										$("#wizard_content").html(data);
									});
									
								}else{
									
									renderPreviewPage(transactionId).done(function(result){
										
										$("#wizard_content").html("");
										$("#wizard_content").html(result);
										$("#wizard_content").hide().fadeIn(2000);
															
									});
									
								}
								
							});
							
						//});
						
						
					}
				
				});
				
			});
			
		});
									
							
	}).fail(function(jqXHR, textStatus, errorThrown) {

				console.log(errorThrown);

   });

}


/**
 * 
 * entry point of wizard step
 * 
 * @param order order of the wizard
 * @param isVenue is step for venue or not
 * @returns
 * 
 */
function processWizardTransaction(currentOrder,isNext) {
	
	var condition=currentOrder==1 && ($("#wizard-date").val() == null || $("#wizard-date").val() === "");
	
	console.log(currentOrder);
	console.log($("#wizard-date").val());
	console.log(condition);
	
	if(condition){
		
		swal('Oops...','you have to define event date first!','error');
		
	}else{
	
		$("#plan-quote-info-selected").text("");
	
		var loading = $('#spinner-progress').html();
	
		$("#wizard_content").html(loading);
	
		var value = $("#plan_value_container").val();
				
		proceedWizard(currentOrder, value,isNext,false);
	
	}
}


/**
 * 
 * skip wizard step
 * 
 * @param order order of the wizard
 * @param isVenue is step for venue or not
 * @returns
 * 
 */
function skipWizardTransaction(currentOrder,isNext) {
	

	
		$("#plan-quote-info-selected").text("");
	
		var loading = $('#spinner-progress').html();
	
		$("#wizard_content").html(loading);
	
		var value = $("#plan_value_container").val();
				
		proceedWizard(currentOrder, value,isNext,true);
	
	
}


/**
 * 
 * update the value of custom event venue location if venue is not choosen
 * 
 * @param transactionId
 * @returns
 */
function defineCustomLocation(transactionId){
	
	
	updateCustomLocation(transactionId,$("#custom-event-location").val()).done(function(data){
		
		console.log(data);
		
		renderPreviewPage(data.content).done(function(result){
			
			$("#wizard_content").html("");
			$("#wizard_content").html(result);
			$("#wizard_content").hide().fadeIn(2000);
			
		});
		
	});
	
}


/**
 * 
 * finalize wizard transaction execute on priewview page
 * 
 * @param transactionId
 * @returns
 */
function finalizeWizardStep(transactionId){
	completeWizardRequest(transactionId).done(function(data){
		$("#plan_event_transaction_form").submit();
	});
}	
	