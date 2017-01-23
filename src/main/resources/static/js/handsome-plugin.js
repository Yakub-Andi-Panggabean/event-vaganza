/**
 * 
 * list of custom jquery plugin created by yakub
 * 
 */

$.fn.validateEmail = function(messageId, formGroup, label) {

	var trigger = false;

	var regex = '^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$';
	var email = this.val();

	if (email === '') {
		$('#' + messageId).html(label + " may not be empty");
		$('#' + messageId).show();
		trigger = false;
		$('#' + formGroup).addClass('has-error');
	} else if (!email.match(regex)) {
		$('#' + messageId).html(label + " format is not valid");
		$('#' + messageId).show();
		$('#' + formGroup).addClass('has-error');
		trigger = false;
	} else {
		$('#' + messageId).html("");
		$('#' + messageId).hide();
		$('#' + formGroup).removeClass('has-error');
		trigger = true;
	}

	return trigger;
}

$.fn.validateInputNotEmpty = function(messageId, formGroup, length, label) {

	var input = this.val();
	var trigger = false;

	if (input === '') {
		$('#' + messageId).html(label + " may not be empty");
		$('#' + messageId).show();
		$("#" + formGroup).addClass('has-error');
		trigger = false;
	} else if (input.length < length) {
		$('#' + messageId)
				.html(label + " length has to be more than " + length);
		$('#' + messageId).show();
		$("#" + formGroup).addClass('has-error');
		trigger = false;
	} else {
		$('#' + messageId).html("");
		$('#' + messageId).hide();
		$("#" + formGroup).removeClass('has-error');
		trigger = true;
	}

	return trigger;
}

$.fn.validateNumberInput = function(messageId, formGroup, length, label) {
	var trigger = false;

	var regex = '^[0-9]+$';
	var input = this.val();

	if (input.length > 0 && !input.match(regex)) {
		$('#' + messageId).html(label + " has to be number");
		$('#' + messageId).show();
		$("#" + formGroup).addClass('has-error');
		trigger = false;
	} else if (input.length > 0 && input.length < length) {
		$('#' + messageId)
				.html(label + " length has to be more than " + length);
		$('#' + messageId).show();
		$("#" + formGroup).addClass('has-error');
		trigger = false;
	} else {
		$('#' + messageId).html("");
		$('#' + messageId).hide();
		$("#" + formGroup).removeClass('has-error');
		trigger = true;
	}

	return trigger;
}

$.fn.validateOptions = function() {

	var length = this.find('option').length;

	if (length > 0) {
		return true;
	} else {
		return false;
	}

}

/**
 * 
 * used for multiple select type
 * 
 * @returns
 */
$.fn.pickCategory = function(target) {
	var selected = this.find(':selected');
	selected.remove();
	$('#' + target).append(selected);
	$("#" + target).prop("selected", true);

}

/**
 * 
 * used for multiple select type
 * 
 * @returns
 */
$.fn.cancelCategory = function(target) {
	var selected = this.find(':selected');
	selected.remove();
	$('#' + target).append(selected);
	this.prop("selected", true);
}