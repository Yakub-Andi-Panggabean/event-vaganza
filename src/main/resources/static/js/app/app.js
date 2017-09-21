/**
 * 
 * 
 * js main module
 * 
 * 
 */
$(document).ready(function() {
	// do jQuery stuff when DOM is ready
	showModal();
	init();
});

/**
 * 
 * @returns
 */
function init() {

	// $('#spinner-progress').hide();
	$('#bar-progress').hide();
	$('#wizard-process-button-prev').hide();
	$('#wizard-process-button-next').hide();
	$('#text-plan-item').hide();
	$('#wizard-skip-button').hide();
	
	initCategoryPage();

	getUrlVars();
	validateRegisterForm();
	validateVendorRegistrationForm();
	validateBookingRequest();
	searchButtonClick();
	authProcess();
	initSearchPage();
	initVendorRegistrationPage();
	initVendorUpdatePage();
	renderWizardCity();


	$("#booking-date").datetimepicker({
		dateFormat : 'yy/mm/dd',
		timeFormat : "HH:mm"
	});

	$('#wizard-date').datetimepicker({
		dateFormat : 'yy/mm/dd',
		timeFormat : "HH:mm",
		onSelect : function(data) {
			$("#plan_value_container").val(data);
		}
	});

	$('#button_forgot_password').click(function() {
		validatePasswordReset();
	});

	$('#button_forgot_password_new').click(function() {
		resetPassword();
	});

}

/**
 * 
 * get request params value
 * 
 * @returns
 */
function getUrlVars() {
	var vars = {};
	var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,
			function(m, key, value) {
				vars[key] = value;
			});
	return vars;
}

function showModal() {
	$('#login').click(function() {
		$('#login_modal').modal();
	});

	// $('#book-event-date').datepicker();

	$('#button-booking').click(function() {
		$('#book_modal').modal();
	});

	$('#activate_user_button').click(function() {
		$('#login_modal').modal();
	});

	$('#button_password_reset_success').click(function() {
		$('#login_modal').modal();
	});
}

function authProcess() {

	$('#z-auth').click(authenticate);

	$('#z-user').keypress(function(e) {

		var username = $('#z-user').val();
		var password = $('#z-password').val();

		if (e.which == 13) {
			if (username === '') {
				window.alert('user harus di isi');
			} else if (password === '') {
				window.alert('email harus di isi');
			} else {
				authenticate();
			}
		}
	});

	$('#z-password').keypress(function(e) {

		var username = $('#z-user').val();
		var password = $('#z-password').val();

		if (e.which == 13) {
			if (username === '') {
				window.alert('user harus di isi');
			} else if (password === '') {
				window.alert('email harus di isi');
			} else {
				authenticate();
			}
		}
	});

}

/**
 * 
 * method which handle authentication mechanisms
 * 
 * @returns
 */
function authenticate() {
	var username = $('#z-user').val();
	var password = $('#z-password').val();

	var enc = window.btoa(username + ":" + password);

	var loadingAuth = $('#bar-progress').html();
	$('#z-exception').html(loadingAuth);

	$.ajax({
		url : servletContext + 'login',
		method : 'POST',
		headers : {
			'isigunyaziso' : enc
		},
		success : function(data) {
			window.location.replace(data.url);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			var error = JSON.parse(jqXHR.responseText);
			$('#z-exception').html(error.errorMessage);
		}
	});
}

/**
 * 
 * used for logout mechanism
 * 
 * @returns
 */
function invalidate() {
	$.ajax({
		url : servletContext + 'logout',
		method : 'POST',
		success : function(data) {
			window.location.replace(data.url);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error occured : " + errorThrown);
		}
	});
}


function searchButtonClick() {
	$('#search-header-button').click(function() {
		$('#form-search-header').submit();
	});
}

/**
 * Number.prototype.format(n, x)
 * 
 * @param integer
 *            n: length of decimal
 * @param integer
 *            x: length of sections
 */
Number.prototype.format = function(n, x) {
	var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
	return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$&,');
};


function fetchFoundCategoryItemList(uri) {

	var keyword = getUrlVars()["c"];
	var start = getUrlVars()["start"];
	var limit = getUrlVars()["limit"];
	var result = "";

	var progress = $('#spinner-progress').html();

	$('#item_list').html(progress);

	$.ajax({
		url : uri,
		method : 'GET',
		success : function(data) {
			var result = $(data).find('#item_list');
			$('#item_list').html(result.children());
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error occured : " + errorThrown);
		}
	});

}



function loadContentByCategory(start, size) {

	var c = getUrlVars()["c"];
	if (history.pushState) {
		var newurl = window.location.protocol + "//" + window.location.host
				+ window.location.pathname + '?c=' + c + '&start=' + start
				+ '&limit=' + size;
		window.history.pushState({
			path : newurl
		}, '', newurl);
	}

	fetchFoundCategoryItemList(newurl);
}

function createBookingTransaction() {

	var paymentAmount = $('input[name="booking-pay-amount"]:checked').val();
	var paymentMethod = $('input[name="pay-method"]:checked').val();

	console.log('used methode : ' + paymentAmount + ', method :'
			+ paymentMethod);

	$('#payment_amount').val(paymentAmount);
	$('#payment_method').val(paymentMethod);

	$('#booking_transaction_form').submit();

}

function createPlanBookingTransaction() {

	var paymentAmount = $('input[name="plan-pay-amount"]:checked').val();
	var paymentMethod = $('input[name="plan-pay-method"]:checked').val();

	console.log('used methode : ' + paymentAmount + ', method :'
			+ paymentMethod);

	$('#plan_payment_amount').val(paymentAmount);
	$('#plan_payment_method').val(paymentMethod);

	$('#booking_planned_transaction_form').submit();
}

function validatePasswordReset() {

	var email = $('#forgot_password_email').val();

	if (email == null || email === '') {

		$("#forgot_password_email_error_message").show();
		$("#forgot_password_email_error_message")
				.html("email may not be empty");
	} else {

		var encodedMail = window.btoa(email);
		$.ajax({
			url : servletContext + 'api/user/email/' + encodedMail,
			method : 'GET',
			success : function(data) {
				if (data.success === false) {
					$("#forgot_password_email_error_message").show();
					$("#forgot_password_email_error_message")
							.html(data.message);
				} else {
					$("#main_forgot_password_body").remove();
					$("#forgot_password_title").html(
							"Thanks " + data.content.username
									+ ", We already sent email to "
									+ data.content.email
									+ ", Please check your email.");
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("error occured : " + errorThrown);
			}
		});

	}

}

function resetPassword() {

	var newPassword = $('#forgot_password_new').val();
	var confirm = $('#forgot_password_new_confirm').val();

	if (newPassword === confirm) {

		$('#password_reset_form').submit();

	} else {
		swal("", "password confirmation is not matched", "error");
	}

}
