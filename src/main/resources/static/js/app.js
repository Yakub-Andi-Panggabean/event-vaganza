/**
 * 
 */
$(document).ready(function() {
	// do jQuery stuff when DOM is ready
	showModal();

	$('#z-auth').click(authenticate);
});

function showModal() {
	$('#Masuk').click(function() {
		$('#myModal').modal();
	});
}

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
			var error=JSON.parse(jqXHR.responseText);
			$('#z-exception').html(error.errorMessage);
		}
	});
}

function invalidate() {
	$.ajax({
		url : 'logout',
		method : 'POST',
		success : function(data) {
			window.location.replace(data.url);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error occured : "+errorThrown);
		}
	});
}