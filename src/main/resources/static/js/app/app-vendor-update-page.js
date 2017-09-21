/**
 * 
 */


function initVendorUpdatePage(){
	updateVendor();
}

function useVenueUpdate() {
	var id = $('#available_venue_list').val();
	$('#venueVendor').val(id)
	$('#button_venue_vendor_update').text('venue used');
}


//vendor update submit form
function updateVendor() {
	$('#update_vendor_button').click(function() {
		$('#choosen_category_update_list option').each(function() {
			$(this).prop('selected', true);
		});

		$('#form_vendor_update').submit();
	});
}