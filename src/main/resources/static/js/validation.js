/**
 * 
 * contain all validation
 * 
 */

// validate register form
function validateRegisterForm() {

	var isTrue = false;
	var isUserTrue = false;
	var isHandphoneTrue = false;
	var isPhoneTrue = true;
	var isEmailTrue = false;
	var isPasswordTrue = false;
	var isZipCodeTrue = false;
	var isAddressTrue = false;

	// username
	$("#username").keyup(
			function() {

				var username = $("#username").val();

				if (username === '') {
					$('#username_error_message').html("name may not be empty");
					$('#username_error_message').show();
					$("#form_group_username").addClass('has-error');
					isUserTrue = false;
				} else if (username.length < 4) {
					$('#username_error_message').html(
							"name length has to be more than 4");
					$('#username_error_message').show();
					$("#form_group_username").addClass('has-error');
					isUserTrue = false;
				} else {
					$('#username_error_message').html("");
					$('#username_error_message').hide();
					$("#form_group_username").removeClass('has-error');
					isUserTrue = true;
				}

				var isTrue = isUserTrue && isHandphoneTrue && isPhoneTrue
						&& isEmailTrue && isPasswordTrue && isZipCodeTrue
						&& isAddressTrue;
				if (isTrue) {
					$('#register_button').prop('disabled', false)
				} else {
					$('#register_button').prop('disabled', true);
				}

			});

	$("#username").focusout(
			function() {

				var username = $("#username").val();

				if (username === '') {
					$('#username_error_message').html("name may not be empty");
					$('#username_error_message').show();
					$("#form_group_username").addClass('has-error');
					isUserTrue = false;
				} else if (username.length < 4) {
					$('#username_error_message').html(
							"name length has to be more than 4");
					$('#username_error_message').show();
					$("#form_group_username").addClass('has-error');
					isUserTrue = false;
				} else {
					$('#username_error_message').html("");
					$('#username_error_message').hide();
					$("#form_group_username").removeClass('has-error');
					isUserTrue = true;

				}

				var isTrue = isUserTrue && isHandphoneTrue && isPhoneTrue
						&& isEmailTrue && isPasswordTrue && isZipCodeTrue
						&& isAddressTrue;
				if (isTrue) {
					$('#register_button').prop('disabled', false)
				} else {
					$('#register_button').prop('disabled', true);
				}

			});

	// handphone
	$("#handphone").keyup(
			function() {

				var regex = '^[0-9]+$';
				var handphone = $("#handphone").val();

				if (handphone === '') {
					$('#handphone_error_message').html(
							"handphone may not be empty");
					$('#handphone_error_message').show();
					$("#form_group_handphone").addClass('has-error');
					isHandphoneTrue = false;
				} else if (!handphone.match(regex)) {
					$('#handphone_error_message')
							.html("phone has to be number");
					$('#handphone_error_message').show();
					$("#form_group_handphone").addClass('has-error');
					isHandphoneTrue = false;
				} else if (handphone.length < 12) {
					$('#handphone_error_message').html(
							"phone length has to be more than 12");
					$('#handphone_error_message').show();
					$("#form_group_handphone").addClass('has-error');
					isHandphoneTrue = false;
				} else {
					$('#handphone_error_message').html("");
					$('#handphone_error_message').hide();
					$("#form_group_handphone").removeClass('has-error');
					isHandphoneTrue = true;
				}

				var isTrue = isUserTrue && isHandphoneTrue && isPhoneTrue
						&& isEmailTrue && isPasswordTrue && isZipCodeTrue
						&& isAddressTrue;
				if (isTrue) {
					$('#register_button').prop('disabled', false)
				} else {
					$('#register_button').prop('disabled', true);
				}

			});

	$("#handphone").focusout(
			function() {

				var regex = '^[0-9]+$';
				var handphone = $("#handphone").val();

				if (handphone === '') {
					$('#handphone_error_message').html(
							"handphone may not be empty");
					$('#handphone_error_message').show();
					$("#form_group_handphone").addClass('has-error');
					isHandphoneTrue = false;
				} else if (!handphone.match(regex)) {
					$('#handphone_error_message').html(
							"handphone has to be number");
					$('#handphone_error_message').show();
					$("#form_group_handphone").addClass('has-error');
					isHandphoneTrue = false;
				} else if (handphone.length < 12) {
					$('#handphone_error_message').html(
							"handphone length has to be more than 12");
					$('#handphone_error_message').show();
					$("#form_group_handphone").addClass('has-error');
					isHandphoneTrue = false;
				} else {
					$('#handphone_error_message').html("");
					$('#handphone_error_message').hide();
					$("#form_group_handphone").removeClass('has-error');
					isHandphoneTrue = true;
				}

				var isTrue = isUserTrue && isHandphoneTrue && isPhoneTrue
						&& isEmailTrue && isPasswordTrue && isZipCodeTrue
						&& isAddressTrue;
				if (isTrue) {
					$('#register_button').prop('disabled', false)
				} else {
					$('#register_button').prop('disabled', true);
				}

			});

	// phone
	$("#phone").keyup(
			function() {

				var regex = '^[0-9]+$';
				var phone = $("#phone").val();

				if (phone.length > 0 && !phone.match(regex)) {
					$('#phone_error_message').html("phone has to be number");
					$('#phone_error_message').show();
					$("#form_group_phone").addClass('has-error');
					isPhoneTrue = false;
				} else if (phone.length > 0 && phone.length < 6) {
					$('#phone_error_message').html(
							"phone length has to be more than 6");
					$('#phone_error_message').show();
					$("#form_group_phone").addClass('has-error');
					isPhoneTrue = false;
				} else {
					$('#phone_error_message').html("");
					$('#phone_error_message').hide();
					$("#form_group_phone").removeClass('has-error');
					isPhoneTrue = true;
				}

				var isTrue = isUserTrue && isHandphoneTrue && isPhoneTrue
						&& isEmailTrue && isPasswordTrue && isZipCodeTrue
						&& isAddressTrue;
				if (isTrue) {
					$('#register_button').prop('disabled', false)
				} else {
					$('#register_button').prop('disabled', true);
				}

			});

	$("#phone").focusout(
			function() {

				var regex = '^[0-9]+$';
				var phone = $("#phone").val();

				if (phone.length > 0 && !phone.match(regex)) {
					$('#phone_error_message').html("phone has to be number");
					$('#phone_error_message').show();
					$("#form_group_phone").addClass('has-error');
					isPhoneTrue = false;
				} else if (phone.length > 0 && phone.length < 6) {
					$('#phone_error_message').html(
							"phone length has to be more than 6");
					$('#phone_error_message').show();
					$("#form_group_phone").addClass('has-error');
					isPhoneTrue = false;
				} else {
					$('#phone_error_message').html("");
					$('#phone_error_message').hide();
					$("#form_group_phone").removeClass('has-error');
					isPhoneTrue = true;
				}

				var isTrue = isUserTrue && isHandphoneTrue && isPhoneTrue
						&& isEmailTrue && isPasswordTrue && isZipCodeTrue
						&& isAddressTrue;
				if (isTrue) {
					$('#register_button').prop('disabled', false)
				} else {
					$('#register_button').prop('disabled', true);
				}

			});

	// email
	$("#email")
			.keyup(
					function() {

						var regex = '^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$';
						var email = $("#email").val();

						if (email === '') {
							$('#email_error_message').html(
									"email may not be empty");
							$('#email_error_message').show();
							isEmailTrue = false;
							$("#form_group_email").addClass('has-error');
						} else if (!email.match(regex)) {
							$('#email_error_message').html(
									"email format is not valid");
							$('#email_error_message').show();
							$("#form_group_email").addClass('has-error');
							isEmailTrue = false;
						} else {
							$('#email_error_message').html("");
							$('#email_error_message').hide();
							$("#form_group_email").removeClass('has-error');
							isEmailTrue = true;
						}

						var isTrue = isUserTrue && isHandphoneTrue
								&& isPhoneTrue && isEmailTrue && isPasswordTrue
								&& isZipCodeTrue && isAddressTrue;
						if (isTrue) {
							$('#register_button').prop('disabled', false)
						} else {
							$('#register_button').prop('disabled', true);
						}

					});

	$("#email")
			.focusout(
					function() {

						var regex = '^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$';
						var email = $("#email").val();

						if (email === '') {
							$('#email_error_message').html(
									"email may not be empty");
							$('#email_error_message').show();
							$("#form_group_email").addClass('has-error');
							isEmailTrue = false;
						} else if (!email.match(regex)) {
							$('#email_error_message').html(
									"email format is not valid");
							$('#email_error_message').show();
							$("#form_group_email").addClass('has-error');
							isEmailTrue = false;
						} else {
							$('#email_error_message').html("");
							$('#email_error_message').hide();
							$("#form_group_email").removeClass('has-error');
							isEmailTrue = true;
						}

						var isTrue = isUserTrue && isHandphoneTrue
								&& isPhoneTrue && isEmailTrue && isPasswordTrue
								&& isZipCodeTrue && isAddressTrue;
						if (isTrue) {
							$('#register_button').prop('disabled', false)
						} else {
							$('#register_button').prop('disabled', true);
						}

					});

	// password
	$("#password").keyup(
			function() {

				var password = $("#password").val();

				if (password === '') {
					$('#password_error_message').html(
							"password may not be empty");
					$('#password_error_message').show();
					$("#form_group_password").addClass('has-error');
					isPasswordTrue = false;
				} else if (password.length < 6) {
					$('#password_error_message').html(
							"password length has to be more than 5");
					$('#password_error_message').show();
					$("#form_group_password").addClass('has-error');
					isPasswordTrue = false;
				} else {
					$('#password_error_message').html("");
					$('#password_error_message').hide();
					$("#form_group_password").removeClass('has-error');
					isPasswordTrue = true;
				}

				var isTrue = isUserTrue && isHandphoneTrue && isPhoneTrue
						&& isEmailTrue && isPasswordTrue && isZipCodeTrue
						&& isAddressTrue;
				if (isTrue) {
					$('#register_button').prop('disabled', false)
				} else {
					$('#register_button').prop('disabled', true);
				}

			});

	$("#password").focusout(
			function() {

				var password = $("#password").val();

				if (password === '') {
					$('#password_error_message').html(
							"password may not be empty");
					$('#password_error_message').show();
					$("#form_group_password").addClass('has-error');
					isPasswordTrue = false;
				} else if (password.length < 6) {
					$('#password_error_message').html(
							"password length has to be more than 5");
					$('#password_error_message').show();
					$("#form_group_password").addClass('has-error');
					isPasswordTrue = false;
				} else {
					$('#password_error_message').html("");
					$('#password_error_message').hide();
					$("#form_group_password").removeClass('has-error');
					isPasswordTrue = true;
				}

				var isTrue = isUserTrue && isHandphoneTrue && isPhoneTrue
						&& isEmailTrue && isPasswordTrue && isZipCodeTrue
						&& isAddressTrue;
				if (isTrue) {
					$('#register_button').prop('disabled', false)
				} else {
					$('#register_button').prop('disabled', true);
				}

			});

	// zipcode
	$("#zipcode").keyup(
			function() {

				var zipcode = $("#zipcode").val();

				if (zipcode === '') {
					$('#zipcode_error_message')
							.html("zipcode may not be empty");
					$('#zipcode_error_message').show();
					$("#form_group_zipcode").addClass('has-error');
					isZipCodeTrue = false;
				} else {
					$('#zipcode_error_message').html("");
					$('#zipcode_error_message').hide();
					$("#form_group_zipcode").removeClass('has-error');
					isZipCodeTrue = true;
				}

				var isTrue = isUserTrue && isHandphoneTrue && isPhoneTrue
						&& isEmailTrue && isPasswordTrue && isZipCodeTrue
						&& isAddressTrue;
				if (isTrue) {
					$('#register_button').prop('disabled', false)
				} else {
					$('#register_button').prop('disabled', true);
				}

			});

	$("#zipcode").focusout(
			function() {

				var zipcode = $("#zipcode").val();

				if (zipcode === '') {
					$('#zipcode_error_message')
							.html("zipcode may not be empty");
					$('#zipcode_error_message').show();
					$("#form_group_zipcode").addClass('has-error');
					isZipCodeTrue = false;
				} else {
					$('#zipcode_error_message').html("");
					$('#zipcode_error_message').hide();
					$("#form_group_zipcode").removeClass('has-error');
					isZipCodeTrue = true;
				}

				var isTrue = isUserTrue && isHandphoneTrue && isPhoneTrue
						&& isEmailTrue && isPasswordTrue && isZipCodeTrue
						&& isAddressTrue;
				if (isTrue) {
					$('#register_button').prop('disabled', false)
				} else {
					$('#register_button').prop('disabled', true);
				}

			});

	// address
	$("#user_address").keyup(
			function() {

				var address = $("#user_address").val();

				if (address === '') {
					$('#address_error_message')
							.html("address may not be empty");
					$('#address_error_message').show();
					$("#form_group_address").addClass('has-error');
					isAddressTrue = false;
				} else {
					$('#address_error_message').html("");
					$('#address_error_message').hide();
					$("#form_group_address").removeClass('has-error');
					isAddressTrue = true;
				}

				var isTrue = isUserTrue && isHandphoneTrue && isPhoneTrue
						&& isEmailTrue && isPasswordTrue && isZipCodeTrue
						&& isAddressTrue;
				if (isTrue) {
					$('#register_button').prop('disabled', false)
				} else {
					$('#register_button').prop('disabled', true);
				}

			});

	$("#user_address").focusout(
			function() {

				var address = $("#user_address").val();

				if (address === '') {
					$('#address_error_message')
							.html("address may not be empty");
					$('#address_error_message').show();
					$("#form_group_address").addClass('has-error');
					isAddressTrue = false;
				} else {
					$('#address_error_message').html("");
					$('#address_error_message').hide();
					$("#form_group_address").removeClass('has-error');
					isAddressTrue = true;
				}

				var isTrue = isUserTrue && isHandphoneTrue && isPhoneTrue
						&& isEmailTrue && isPasswordTrue && isZipCodeTrue
						&& isAddressTrue;
				if (isTrue) {
					$('#register_button').prop('disabled', false)
				} else {
					$('#register_button').prop('disabled', true);
				}

			});

}

function validateVendorRegistrationForm() {

	var isVendorEmailValid = false;
	var isVendorNameValid = false;
	var isVendorHandphoneValid = false;
	var isVendorPhoneValid = false;
	var isVendorPicValid = false;
	var isVendorDescValid = false;
	var isVendorAddressValid = false;
	var isVendorTypeValid = false;
	var isVendorInputValid = false;

	// vendor name
	$("#vendorname").keyup(
			function() {
				isVendorNameValid = $('#vendorname').validateInputNotEmpty(
						'vendorname_error_message', 'form_group_vendorname', 8,
						'Vendor Name');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	$("#vendorname").focusout(
			function() {
				isVendorNameValid = $('#vendorname').validateInputNotEmpty(
						'vendorname_error_message', 'form_group_vendorname', 8,
						'Vendor Name');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	// email
	$("#vendor_email").keyup(
			function() {
				isVendorEmailValid = $('#vendor_email').validateEmail(
						'vendor_email_error_message',
						'form_group_vendor_email', 'Vendor Email');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	$("#vendor_email").focusout(
			function() {
				isVendorEmailValid = $('#vendor_email').validateEmail(
						'vendor_email_error_message',
						'form_group_vendor_email', 'Vendor Email');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	// mobile number
	$("#vendor_handphone").keyup(
			function() {
				isVendorHandphoneValid = $('#vendor_handphone')
						.validateNumberInput('vendor_handphone_error_message',
								'form_group_vendor_handphone', 12,
								'Vendor handphone');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	$("#vendor_handphone").focusout(
			function() {
				isVendorHandphoneValid = $('#vendor_handphone')
						.validateNumberInput('vendor_handphone_error_message',
								'form_group_vendor_handphone', 12,
								'Vendor handhone');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	// phone number
	$("#vendor_phone").keyup(
			function() {
				isVendorPhoneValid = $('#vendor_phone').validateNumberInput(
						'vendor_phone_error_message',
						'form_group_vendor_phone', 6, 'Vendor phone');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	$("#vendor_phone").focusout(
			function() {
				isVendorPhoneValid = $('#vendor_phone').validateNumberInput(
						'vendor_phone_error_message',
						'form_group_vendor_phone', 6, 'Vendor phone');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	// pic
	$("#vendor_pic").keyup(
			function() {
				isVendorPicValid = $('#vendor_pic').validateInputNotEmpty(
						'vendor_pic_error_message', 'form_group_vendor_pic', 6,
						'Vendor Pic');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	$("#vendor_pic").focusout(
			function() {
				isVendorPicValid = $('#vendor_pic').validateInputNotEmpty(
						'vendor_pic_error_message', 'form_group_vendor_pic', 6,
						'Vendor Pic');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	// description
	$("#vendor_description").keyup(
			function() {
				isVendorDescValid = $('#vendor_description')
						.validateInputNotEmpty(
								'vendor_descrption_error_message',
								'form_group_vendor_description', 6,
								'Vendor Description');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	$("#vendor_description").focusout(
			function() {
				isVendorDescValid = $('#vendor_description')
						.validateInputNotEmpty(
								'vendor_descrption_error_message',
								'form_group_vendor_description', 6,
								'Vendor Description');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	// description
	$("#vendor_address").keyup(
			function() {
				isVendorAddressValid = $('#vendor_address')
						.validateInputNotEmpty('vendor_address_error_message',
								'form_group_vendor_address', 6,
								'Vendor Address');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	$("#vendor_address").focusout(
			function() {
				isVendorAddressValid = $('#vendor_address')
						.validateInputNotEmpty('vendor_address_error_message',
								'form_group_vendor_address', 6,
								'Vendor Address');

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	$("#button_pick_vendor_type").click(
			function() {
				isVendorTypeValid = $("#choosen_category_list")
						.validateOptions();

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

	$("#button_cancel_vendor_type").click(
			function() {
				isVendorTypeValid = $("#choosen_category_list")
						.validateOptions();

				isVendorInputValid = isVendorEmailValid && isVendorNameValid
						&& isVendorHandphoneValid && isVendorPhoneValid
						&& isVendorPicValid && isVendorDescValid
						&& isVendorAddressValid && isVendorTypeValid;

				if (isVendorInputValid) {
					$('#register_vendor_button').prop('disabled', false)
				} else {
					$('#register_vendor_button').prop('disabled', true)
				}

			});

}

/**
 * 
 * authenticate booking request
 * 
 * @returns
 */
function authenticateBooking() {
	var username = $('#z-user').val();
	var password = $('#z-password').val();

	var enc = window.btoa(username + ":" + password);
	
	var loadingAuth = $('#bar-progress').html();
	$('#z-exception').html(loadingAuth);

	$.ajax({
		url : '/' + servletContext + '/login',
		method : 'POST',
		headers : {
			'isigunyaziso' : enc
		},
		success : function(data) {
			$('#booking-request-form').submit();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			var error = JSON.parse(jqXHR.responseText);
			$('#z-exception').html(error.errorMessage);
		}
	});
}

/**
 * 
 * validate booking request
 * 
 * @returns
 */
function validateBookingRequest() {
	$("#confirm-booking-request")
			.click(
					function() {

						var eventDate = $('#booking-date').val();

						console.log('event date :' + eventDate)

						if (eventDate == '' || eventDate == null) {
							$('#booking-request-error-message').text(
									'event date may not be empty');
						} else {

							$
									.ajax({
										url : '/' + servletContext
												+ '/api/auth/status',
										method : 'POST',
										success : function(data) {
											console.log(data.content);
											if (data.content == '0') {
												$('#z-exception')
														.html(
																'<label>You have to login in order to be able make booking request</label>');
												$('#login_modal').modal();

												$('#z-auth').unbind('click');
												$('#z-auth').click(
														authenticateBooking);

												$('#z-user').unbind('keypress');
												$('#z-user').keypress(function(e) {
																	if (e.which == 13) {
																		authenticateBooking();
																	}
																});

												$('#z-password').unbind(
														'keypress');
												$('#z-password').keypress(function(e) {
													if (e.which == 13) {
														authenticateBooking();
													}
												});

											} else {
												$('#booking-request-form')
														.submit();
											}

										},
										error : function(jqXHR, textStatus,
												errorThrown) {
										}
									});
						}

					});
}

/**
 * 
 * authenticate booking request
 * 
 * @returns
 */
function authenticatePlanEvent(category) {
	var username = $('#z-user').val();
	var password = $('#z-password').val();

	var enc = window.btoa(username + ":" + password);
	
	var loadingAuth = $('#bar-progress').html();
	$('#z-exception').html(loadingAuth);

	$.ajax({
		url : '/' + servletContext + '/login',
		method : 'POST',
		headers : {
			'isigunyaziso' : enc
		},
		success : function(data) {
			window.location.replace('/' + servletContext + '/plan-forwarder/'
					+ category);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			var error = JSON.parse(jqXHR.responseText);
			$('#z-exception').html(error.errorMessage);
		}
	});
}

function validatePlanEvent(category) {

	$
			.ajax({
				url : '/' + servletContext + '/api/auth/status',
				method : 'POST',
				success : function(data) {
					if (data.content == '0') {
						$('#z-exception')
								.html(
										'<label>You have to login in order to be able  plan event</label>');

						$('#login_modal').modal();

						$('#z-auth').unbind('click');
						$('#z-auth').click(function() {
							authenticatePlanEvent(category);
						});

						$('#z-user').unbind('keypress');
						$('#z-user').keypress(function(e) {
							if (e.which == 13) {
								authenticatePlanEvent(category);
							}
						});

						$('#z-password').unbind('keypress');
						$('#z-password').keypress(function(e) {
							if (e.which == 13) {
								authenticatePlanEvent(category);
							}
						});

					} else {

						console.log('/' + servletContext + '/plan-forwarder/'
								+ category);

						window.location.replace('/' + servletContext
								+ '/plan-forwarder/' + category);
					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
				}
			});

}
