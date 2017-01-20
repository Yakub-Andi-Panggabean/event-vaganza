//contents of the displayed record
var pagingPageSize = 12;
// pagination number
var pagingNumber = 5;

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
	fetchItemList(0, pagingPageSize, 1);
	filteringKeyword();
	filteringPrice();
	sortingPackageList();
	filteringPackageType();
	filteringCapacity();
}

function showModal() {
	$('#login').click(function() {
		$('#login_modal').modal();
	});

	$('#book-event-date').datepicker();

	$('#button-booking').click(function() {
		$('#book_modal').modal();
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
		url : '/login',
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
		url : '/logout',
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
		url : '/api/categories',
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

/**
 * 
 * used for multiple select type
 * 
 * @returns
 */
function pickCategory() {
	var selected = $('#available_category_list').find(':selected');
	selected.remove();
	$('#choosen_category_list').append(selected);
	$("#choosen_category_list").prop("selected", true);

}

/**
 * 
 * used for multiple select type
 * 
 * @returns
 */
function cancelCategory() {
	var selected = $('#choosen_category_list').find(':selected');
	selected.remove();
	$('#available_category_list').append(selected);
	$("#choosen_category_list").prop("selected", true);
}

/**
 * 
 * fetch record and pagination
 * 
 * @param start
 * @param limit
 * @returns
 */
function fetchItemList(start, limit) {
	var fType = getUrlVars()["c"];

	// update dispalay record
	$.ajax({
		url : '/api/items/' + start + '/' + limit + '/' + fType,
		method : 'GET',
		success : function(data) {
			var result = "";
			var pagination = "";
			var displayedpagination = "";
			$.each(data.contents.content, function(index, value) {
				result = result.concat("<div class=\"col-sm-6 col-md-3\">");
				result = result.concat("<a href=" + value.url + ">");
				result = result.concat("<div class=\"thumbnail\">");
				result = result.concat("<img class=\"img-fluid\" src=\""
						+ value.image + "\" alt=\"Responsive image\">");
				result = result.concat("</a>");
				result = result.concat("</div>");
				result = result.concat("<div class=\"caption\">");
				result = result.concat("<h4>" + value.name + "</h4>");
				result = result.concat("<p>" + value.price + ".</p>");
				result = result.concat("</div>");
				result = result.concat("</div>");
			});

			// clear array
			pages = [];
			// populate all contents into an array
			for (i = 1; i <= data.contents.totalPages; i++) {

				pagination = "<li id=\"pagination_" + (i - 1)
						+ "\" ><a onclick=\"fetchItemList(" + (i - 1) + ","
						+ pagingPageSize + ")\">" + i + "</a></li>";

				pages.push(pagination);

			}

			for (i = 0; i < data.contents.totalPages; i++) {
				displayedpagination = displayedpagination.concat(pages[i]);
			}

			$('#item_list').html(result);

			$('#pagination').twbsPagination({
				totalPages : data.contents.totalPages,
				visiblePages : pagingNumber,
				onPageClick : function(event, page) {
					fetchItemList((page - 1), pagingPageSize);
				}
			});

		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error occured : " + errorThrown);
		}
	});
}

function filteringKeyword() {
	$('#button_filter_keyword_items').click(function() {
		fetchItemsSelectionList(0, pagingPageSize, true);
	});

	$('#filter_keyword_items').keypress(function() {
		fetchItemsSelectionList(0, pagingPageSize, true);
	});

	$('#filter_keyword_items').keyup(function() {
		fetchItemsSelectionList(0, pagingPageSize, true);
	});
}

function filteringCapacity() {

	$('#filter_capacity_selection').keypress(function(e) {
		if (e.which === 13) {
			fetchItemsSelectionList(0, pagingPageSize, true);
		}
	});

}

function filteringPrice() {

	$('#filter_min_price_selection').keypress(function(e) {
		if (e.which === 13) {
			fetchItemsSelectionList(0, pagingPageSize, true);
		}
	});

	$('#filter_max_price_selection').keypress(function(e) {
		if (e.which === 13) {
			fetchItemsSelectionList(0, pagingPageSize, true);
		}
	});

}

function filteringPackageType() {
	$('#filter_package_type_selection').change(function(e) {
		fetchItemsSelectionList(0, pagingPageSize, true);
	});
}

function sortingPackageList() {
	$('#filtering_package_sorting').change(function(e) {
		fetchItemsSelectionList(0, pagingPageSize, true);
	});
}

/**
 * 
 * filter data
 * 
 * @param start
 * @param limit
 * @returns
 */
function fetchItemsSelectionList(start, limit, destroy) {
	var fType = getUrlVars()["c"];
	var filterKeyword = $('#filter_keyword_items').val();
	var filterMinPrice = $('#filter_min_price_selection').val();
	var filterMaxPrice = $('#filter_max_price_selection').val();
	var filterCapacity = $('#filter_capacity_selection').val();
	var filterCity = $('filter_city_selection').val();
	var packageSorting = $('#filtering_package_sorting').find(':selected')
			.val();
	var filterPackageType = $('#filter_package_type_selection').find(
			':selected').val();

	var filterData = {
		"keyword" : filterKeyword,
		"minPrice" : Number(filterMinPrice),
		"maxPrice" : Number(filterMaxPrice),
		"capacity" : Number(filterCapacity),
		"city" : filterCity,
		"packageType" : filterPackageType
	}

	var filterSorting;

	console.log("sorting :" + packageSorting);

	if (packageSorting === 'cheapest') {
		filterSorting = {
			propertyName : "price",
			order : "asc"
		}
	} else if (packageSorting === 'most_expensive') {
		filterSorting = {
			propertyName : "price",
			order : "desc"
		}
	} else if (packageSorting === 'discount') {
		filterSorting = {
			propertyName : "discount",
			order : "asc"
		}
	} else if (packageSorting === 'capacity') {
		filterSorting = {
			propertyName : "capacity",
			order : "asc"
		}
	} else {
		packageSorting = null;
	}

	filterData.sorting = filterSorting;

	$.ajax({
		url : '/api/items/' + start + '/' + limit + '/' + fType,
		method : "POST",
		contentType : 'application/json',
		dataType : 'json',
		data : JSON.stringify(filterData),
		success : function(data) {
			var result = "";
			var pagination = "";
			var displayedpagination = "";
			$.each(data.contents.content, function(index, value) {
				result = result.concat("<div class=\"col-sm-6 col-md-3\">");
				result = result.concat("<a href=" + value.url + ">");
				result = result.concat("<div class=\"thumbnail\">");
				result = result.concat("<img class=\"img-fluid\" src=\""
						+ value.image + "\" alt=\"Responsive image\">");
				result = result.concat("</a>");
				result = result.concat("</div>");
				result = result.concat("<div class=\"caption\">");
				result = result.concat("<h4>" + value.name + "</h4>");
				result = result.concat("<p>" + value.price + ".</p>");
				result = result.concat("</div>");
				result = result.concat("</div>");
			});

			// clear array
			pages = [];
			// populate all contents into an array
			for (i = 1; i <= data.contents.totalPages; i++) {

				pagination = "<li id=\"pagination_" + (i - 1)
						+ "\" ><a onclick=\"fetchItemsSelectionList(" + (i - 1)
						+ "," + pagingPageSize + ")\">" + i + "</a></li>";

				pages.push(pagination);

			}

			for (i = 0; i < data.contents.totalPages; i++) {
				displayedpagination = displayedpagination.concat(pages[i]);
			}

			$('#item_list').html(result);

			if (destroy == true) {
				$('#pagination').twbsPagination('destroy');
			}

			$('#pagination').twbsPagination(
					{
						totalPages : data.contents.totalPages,
						visiblePages : pagingNumber,
						onPageClick : function(event, page) {
							if (page > 1) {
								fetchItemsSelectionList((page - 1),
										pagingPageSize, false);
							}
						}
					});

		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error occured : " + errorThrown);
		}
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
