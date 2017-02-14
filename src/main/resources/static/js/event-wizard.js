/**
 * 
 * js functionality for wizard
 * 
 */

// reset
function resetStep() {
	$("#wizard-steps li").each(function() {
		$(this).removeClass("active");
	});
}

// event
function firstWizardStep() {

	var value = $('#choosen_type_value').val();

	// reset all step
	resetStep();

	// activate step 2 header
	$('#package_plan_step').addClass("active");

	$.ajax({
		url : 'ajax/wizard-second/' + value,
		method : 'GET',
		success : function(data) {
			$('.wizard-title').text('Choose Your Package');
			$('#wizard_content').html(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(errorThrown);
		}
	});
}

function choseEventType(id) {

	$('#choosen_type_value').val(id);

	$('#wizard-process-button').unbind('click');
	$('#wizard-process-button').click(firstWizardStep);

}

// package
function proccessWizardSecondStep() {

	// reset all step
	resetStep();

	// activate step 3 header
	$('#venue_plan_step').addClass("active");

	$.ajax({
		url : 'ajax/wizard-third',
		method : 'GET',
		success : function(data) {
			$('.wizard-title').text('Choose Your Venue');
			$('#wizard_content').html(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(errorThrown);
		}
	});

}

function secondWizardStep(id) {

	$('#choosen_package_value').val(id);

	var value = $('#choosen_package_value').val();

	$('#wizard-process-button').unbind('click');
	$('#wizard-process-button').click(proccessWizardSecondStep);

}

// venue
function proccessWizardThirdStep() {

	// reset all step
	resetStep();

	// activate step 4 header
	$('#date_plan_step').addClass("active");

	$.ajax({
		url : 'ajax/wizard-fourth',
		method : 'GET',
		success : function(data) {
			$('.wizard-title').text('Choose Event Date');
			$('#wizard_content').html(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(errorThrown);
		}
	});

}

function thirdWizardStep(id) {

	$('#choosen_venue_value').val(id);

	$('#wizard-process-button').unbind('click');
	$('#wizard-process-button').click(proccessWizardThirdStep);

}

// confirm
function proccessWizardFourthStep() {

	// reset all step
	resetStep();

	// activate step 4 header
	$('#confirmation_plan_step').addClass("active");

	var packages = $('#choosen_package_value').val();
	var venue = $('#choosen_venue_value').val();
	var date = $('#choosen_date_value').val();
	var capacity = $('#choosen_capacity_value').val();

	var params = '?packages=' + packages + '&venue=' + venue + '&date=' + date
			+ '&capacity=' + capacity;
	
	console.log(params);

	$.ajax({
		url : 'ajax/wizard-fifth' + params.trim(),
		method : 'GET',
		success : function(data) {
			$('.wizard-title').text('Package Confirmation');
			$('#wizard_content').html(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(errorThrown);
		}
	});

}

function fourthWizardStep() {

	var date = $('#wizard-date').val();
	var capacity = $('#wizard-capacity').val();

	$('#choosen_date_value').val(date);
	$('#choosen_capacity_value').val(capacity);

	console.log('change');

	$('#wizard-process-button').unbind('click');
	$('#wizard-process-button').click(proccessWizardFourthStep);

}
