/**
 * 
 */


function initVendorRegistrationPage(){
	
	
	fetchAllAvailableCategory();
	loadVenueList();
	loadVenueListInfo();
	
	$('#button_pick_update_vendor_type').click(
			function() {
				$('#available_category_update_list').pickCategory(
						'choosen_category_update_list');
			});

	$('#button_cancel_update_vendor_type').click(
			function() {
				$('#choosen_category_update_list').cancelCategory(
						'available_category_update_list');
			});

	$('#button_pick_vendor_type').click(function() {
		$('#available_category_list').pickCategory('choosen_category_list');
	});

	$('#button_cancel_vendor_type').click(function() {
		$('#choosen_category_list').cancelCategory('available_category_list');
	});
	
}


function fetchAllAvailableCategory() {
	$.ajax({
		url : servletContext + 'api/categories',
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

function loadVenueList() {

	$.ajax({
		url : servletContext + 'api/venues',
		method : 'GET',
		success : function(data) {
			var result = "";
			$.each(data.contents.content, function(index, value) {
				result = result.concat("<option value='" + value.venueId + "'>"
						+ value.venueName + "</option>");
			});
			$('#available_venue_list').html(result);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error occured : " + errorThrown);
		}
	});

}

function loadVenueListInfo() {

	$('#available_venue_list').change(function() {

		var id = $('#available_venue_list').val();

		console.log('this is id of selected venue' + id);

		$.ajax({
			url : servletContext + 'api/venues',
			method : 'GET',
			success : function(data) {
				var result = "";
				$.each(data.contents.content, function(index, value) {
					if (value.venueId === id) {

						$('#vendor_venue_name').text(value.venueName);
						$('#vendor_venue_city').text(value.city);
						$('#vendor_venue_address').text(value.venueAddress);
						$('#button_venue_vendor').prop('disabled', false)

					}
				});
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("error occured : " + errorThrown);
			}
		});

	});
}

function useVenue() {
	var id = $('#available_venue_list').val();
	$('#venue_vendor_value').val(id)
	$('#button_venue_vendor').text('venue used');
}


