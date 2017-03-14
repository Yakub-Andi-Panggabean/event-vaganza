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

function findPackage(type) {
	var loading = $('#spinner-progress').html();
	var progress = $('#wizard_content').html(loading);

	$('#wizard-skip-button').prop('disabled', false);

	var venue = $('#wizard-event-venue').val();

	$('#wizard-process-button-prev').prop('disabled', true);

	console.log('venue : ' + venue);
	console.log('type : ' + type);

	if (venue != '' && type != 'venue') {

		$.ajax({
			url : 'ajax/wizard-package/' + venue + '/' + type,
			success : function(data) {
				console.log('using venue');
				$('#wizard_content').html(data);
				$('#wizard-process-button-prev').prop('disabled', false);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
				$('#wizard-process-button-prev').prop('disabled', false);
			}
		});

	} else {

		$.ajax({
			url : 'ajax/wizard-package/' + type,
			success : function(data) {
				console.log('without venue');
				$('#wizard_content').html(data);
				$('#wizard-process-button-prev').prop('disabled', false);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
				$('#wizard-process-button-prev').prop('disabled', false);
			}
		});

	}
}

/**
 * 
 * move to custom location section
 * 
 * @returns
 */
function defineCustomLocation() {
	$.ajax({
		url : 'ajax/wizard-package/custom-location',
		success : function(data) {
			$('#wizard_content').html(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(errorThrown);
		}
	});
}

function submitAllPlan() {
	$('#plan_event_form').submit();
}

function submitAllPlanWithLocation() {

	var location = $('#custom-event-location').val();

	if (location == '') {
		alert('please fill custom location');
	} else {
		$('#wizard-event-location').val(location);

		$('#plan_event_form').submit();
	}

}

// custom address
function additionalWizardStep() {

	// reset all step
	resetStep();

	$('#wizard-skip-button').hide();

	$('#wizard-process-button-next').show();
	$('#text-plan-item').html("");
	$('#wizard-process-button-next').prop('disabled', false);

	$('#wizard-event-status').val('location');

	// move to custom location section
	defineCustomLocation();

	$('#wizard-process-button-next').unbind('click');
	$('#wizard-process-button-next').click(submitAllPlanWithLocation);

	$('#wizard-process-button-prev').unbind('click');
	$('#wizard-process-button-prev').click(function() {
		$('#wizard-event-transport').val("");
		eightWizardStep();
	});
}

// transport
function eightWizardStep() {

	// reset all step
	resetStep();

	// activate step 2 header
	$('#package_plan_transport').addClass("active");
	$('#wizard-process-button-next').show();
	$('#wizard-skip-button').show();
	$('#text-plan-item').html("");
	$('#wizard-process-button-next').prop('disabled', true);

	$('#wizard-event-status').val('transport');
	// others type
	findPackage('050');

	var venue = $('#wizard-event-venue').val();

	if (venue != '') {

		console.log('venue is not empty');

		$('#wizard-process-button-next').unbind('click');
		$('#wizard-process-button-next').click(submitAllPlan);

		$('#wizard-skip-button').unbind('click');
		$('#wizard-skip-button').click(submitAllPlan);

	} else {

		console.log('use custom location');

		$('#wizard-process-button-next').unbind('click');
		$('#wizard-process-button-next').click(additionalWizardStep);

		$('#wizard-skip-button').unbind('click');
		$('#wizard-skip-button').click(additionalWizardStep);

	}

	$('#wizard-process-button-prev').unbind('click');
	$('#wizard-process-button-prev').click(function() {
		$('#wizard-event-others').val("");
		seventhWizardStep();
	});
}

// others
function seventhWizardStep() {

	// reset all step
	resetStep();

	// activate step 2 header
	$('#package_plan_others').addClass("active");
	$('#wizard-process-button-next').show();
	$('#wizard-skip-button').show();
	$('#text-plan-item').html("");
	$('#wizard-process-button-next').prop('disabled', true);

	$('#wizard-event-status').val('others');
	// others type
	findPackage('111');

	$('#wizard-process-button-next').unbind('click');
	$('#wizard-process-button-next').click(eightWizardStep);

	$('#wizard-skip-button').unbind('click');
	$('#wizard-skip-button').click(eightWizardStep);

	$('#wizard-process-button-prev').unbind('click');
	$('#wizard-process-button-prev').click(function() {
		$('#wizard-event-eo').val("");
		sixthWizardStep();
	});
}

// e.o
function sixthWizardStep() {

	// reset all step
	resetStep();

	// activate step 2 header
	$('#package_plan_eo').addClass("active");
	$('#wizard-process-button-next').show();
	$('#wizard-skip-button').show();
	$('#text-plan-item').html("");
	$('#wizard-process-button-next').prop('disabled', true);

	$('#wizard-event-status').val('eo');
	// e.o type
	findPackage('080');

	$('#wizard-process-button-next').unbind('click');
	$('#wizard-process-button-next').click(seventhWizardStep);

	$('#wizard-skip-button').unbind('click');
	$('#wizard-skip-button').click(seventhWizardStep);

	$('#wizard-process-button-prev').unbind('click');
	$('#wizard-process-button-prev').click(function() {
		$('#wizard-event-photo').val("");
		fifthWizardStep();
	});
}

// photo
function fifthWizardStep() {

	// reset all step
	resetStep();

	// activate step 2 header
	$('#package_plan_photo').addClass("active");
	$('#wizard-process-button-next').show();
	$('#wizard-skip-button').show();
	$('#text-plan-item').html("");
	$('#wizard-process-button-next').prop('disabled', true);

	$('#wizard-event-status').val('photo');
	// photo type
	findPackage('060');

	$('#wizard-process-button-next').unbind('click');
	$('#wizard-process-button-next').click(sixthWizardStep);

	$('#wizard-skip-button').unbind('click');
	$('#wizard-skip-button').click(sixthWizardStep);

	$('#wizard-process-button-prev').unbind('click');
	$('#wizard-process-button-prev').click(function() {
		$('#wizard-event-makeup').val("");
		fourthWizardStep();
	});
}

// make up
function fourthWizardStep() {

	// reset all step
	resetStep();

	// activate step 2 header
	$('#package_plan_makeup').addClass("active");
	$('#wizard-process-button-next').show();
	$('#wizard-skip-button').show();
	$('#text-plan-item').html("");
	$('#wizard-process-button-next').prop('disabled', true);

	$('#wizard-event-status').val('makeup');
	// make up type
	findPackage('070');

	$('#wizard-process-button-next').unbind('click');
	$('#wizard-process-button-next').click(fifthWizardStep);

	$('#wizard-skip-button').unbind('click');
	$('#wizard-skip-button').click(fifthWizardStep);

	$('#wizard-process-button-prev').unbind('click');
	$('#wizard-process-button-prev').click(function() {
		$('#wizard-event-decoration').val("");
		thirdWizardStep();
	});
}

// decoration
function thirdWizardStep() {

	// reset all step
	resetStep();

	// activate step 2 header
	$('#package_plan_decoration').addClass("active");
	$('#wizard-process-button-next').show();
	$('#wizard-skip-button').show();
	$('#text-plan-item').html("");
	$('#wizard-process-button-next').prop('disabled', true);

	$('#wizard-event-status').val('decoration');
	// decoration type
	findPackage('030');

	$('#wizard-process-button-next').unbind('click');
	$('#wizard-process-button-next').click(fourthWizardStep);

	$('#wizard-skip-button').unbind('click');
	$('#wizard-skip-button').click(fourthWizardStep);

	$('#wizard-process-button-prev').unbind('click');
	$('#wizard-process-button-prev').click(function() {
		$('#wizard-event-catering').val("");
		secondWizardStep();
	});
}

// catering

function secondWizardStep() {

	// reset all step
	resetStep();

	// activate step 2 header
	$('#package_plan_catering').addClass("active");
	$('#wizard-process-button-next').show();
	$('#wizard-skip-button').show();
	$('#text-plan-item').html("");
	$('#wizard-process-button-next').prop('disabled', true);

	$('#wizard-event-status').val('catering');
	// catering type
	findPackage('010');

	$('#wizard-process-button-next').unbind('click');
	$('#wizard-process-button-next').click(thirdWizardStep);

	$('#wizard-skip-button').unbind('click');
	$('#wizard-skip-button').click(thirdWizardStep);

	$('#wizard-process-button-prev').unbind('click');
	$('#wizard-process-button-prev').click(function() {
		$('#wizard-event-venue').val("");
		firstWizardStep();
	});
}

// venue -------
function previousFirstWizardStep() {
	location.reload();
}

function firstWizardStep() {

	var value = $('#wizard-date').val();

	if (value === '') {
		alert('event date my not be empty');
		return;
	}

	if (value != null && value != '') {
		console.log('date updated');
		$('#wizard-event-date').val(value);
	}

	// reset all step
	resetStep();

	// activate step 2 header
	$('#plan-quote-info').text("Please select your event venue");
	$('#package_plan_date').removeClass("onprogress");
	$('#package_plan_date').addClass("completed");
	$('#package_plan_venue').addClass("onprogress");
	$('#wizard-process-button-prev').show();
	$('#wizard-process-button-next').show();
	$('#wizard-skip-button').show();
	$('#wizard-process-button').hide();

	$('#wizard-event-status').val('venue');
	findPackage('venue');

	$('#wizard-process-button-next').unbind('click');
	$('#wizard-process-button-next').click(secondWizardStep);

	$('#wizard-skip-button').unbind('click');
	$('#wizard-skip-button').click(secondWizardStep);

	$('#wizard-process-button-prev').unbind('click');
	$('#wizard-process-button-prev').click(previousFirstWizardStep);

}

/**
 * 
 * used when select/click a display package on wizard step
 * 
 * @param name
 * @param id
 * @returns
 */
function selectItem(name, id) {
	$('#text-plan-item').show();
	$('#text-plan-item').html(name);
	$('#wizard-process-button-next').prop('disabled', false);
	$('#wizard-skip-button').prop('disabled', true);

	var status = $('#wizard-event-status').val();

	if (status === 'venue') {
		$('#wizard-event-venue').val(id);
	} else if (status === 'catering') {
		$('#wizard-event-catering').val(id);
	} else if (status === 'decoration') {
		$('#wizard-event-decoration').val(id);
	} else if (status === 'makeup') {
		$('#wizard-event-makeup').val(id);
	} else if (status === 'photo') {
		$('#wizard-event-photo').val(id);
	} else if (status === 'eo') {
		$('#wizard-event-eo').val(id);
	} else if (status === 'others') {
		$('#wizard-event-others').val(id);
	} else if (status === 'transport') {
		$('#wizard-event-transport').val(id);
	}
}
