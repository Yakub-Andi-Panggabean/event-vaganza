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