/**
 * 
 */
$(document).ready(function() {
	// do jQuery stuff when DOM is ready
	showModal();
	init();
	$('#z-auth').click(authenticate);
});

/**
 * 
 * @returns
 */
function init() {
	validateRegisterForm();
	validateVendorRegistrationForm();
	fetchAllAvailableCategory();
}

function showModal() {
	$('#login').click(function() {
		$('#myModal').modal();
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

	$.ajax({
		url : 'login',
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
		url : 'logout',
		method : 'POST',
		success : function(data) {
			window.location.replace(data.url);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error occured : " + errorThrown);
		}
	});
}

function fetchAllAvailableCategory() {
	$.ajax({
		url : 'api/categories',
		method : 'GET',
		success : function(data) {
			var result = "";
			$.each(data.contents.content, function(index, value) {
				result = result.concat("<option value='" + value.vendorType
						+ "'>" + value.vendorTypeName + "</option>");
			});
			$('#available_category_list').html(result);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error occured : " + errorThrown);
		}
	});
}

function pickCategory() {
	var selected = $('#available_category_list').find(':selected');
	// console.log("selected id "+selected.attr('id'));
	selected.remove();
	$('#choosen_category_list').append(selected);
	$("#choosen_category_list").prop("selected", true);
	$("#choosen_category_list").validateOptions();

}

function cancelCategory() {
	var selected = $('#choosen_category_list').find(':selected');
	selected.remove();
	$('#available_category_list').append(selected);
	$("#choosen_category_list").prop("selected", true);
	$("#choosen_category_list").validateOptions();
}
