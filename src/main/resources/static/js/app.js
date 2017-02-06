//servlet context should be same with server side
var servletContext = "kado-web";
// contents of the displayed record (should be same withs server side)
var pagingPageSize = 12;
// visible pagination number
var pagingNumber = 5;

/**
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
	getUrlVars();
	validateRegisterForm();
	validateVendorRegistrationForm();
	validateBookingRequest();
	fetchAllAvailableCategory();
	fetchItemList(0, pagingPageSize, 1);
	filteringKeyword();
	filteringPrice();
	sortingPackageList();
	filteringPackageType();
	filteringCapacity();
	searchButtonClick();
	updateVendor();
	authProcess();
	loadPagination();

	 $( "#booking-date" ).datetimepicker({
		 style:'padding-top:0px;'
	 });

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

	$.ajax({
		url : '/' + servletContext + '/login',
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
		url : '/' + servletContext + '/logout',
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
		url : '/' + servletContext + '/api/categories',
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
 * fetch record and pagination
 * 
 * @param start
 * @param limit
 * @returns
 */
function fetchItemList(start, limit) {
	var fType = getUrlVars()["c"];

	console.log('type : ' + fType);

	// update display record
	$
			.ajax({
				url : '/' + servletContext + '/api/items/' + start + '/'
						+ limit + '/' + fType,
				method : 'GET',
				success : function(data) {
					var result = "";
					var pagination = "";
					var displayedpagination = "";
					$
							.each(
									data.contents.content,
									function(index, value) {
										if (index == 0) {
											result = result
													.concat("<div class=\"row\" style=\"margin-bottom:25px\">");
										} else if (index % 4 == 0) {
											result = result.concat("</div>");
											result = result
													.concat("<div class=\"row\" style=\"margin-bottom:25px\">");
										}
										result = result
												.concat("<div class=\"col-sm-6 col-md-3\">");
										result = result.concat("<a href="
												+ value.url + ">");
										result = result
												.concat("<div style=\"box-shadow: -1px 7px 19px rgb(193, 193, 193);\">");
										result = result
												.concat("<img style=\"width: 100%;height: 250px;\" class=\"img-fluid\" src=\""
														+ value.image
																.split(',')[0]
														+ "\" alt=\"Responsive image\">");
										result = result.concat("</a>");
										result = result.concat("</div>");
										result = result
												.concat("<div class=\"caption\">");
										result = result.concat("<h4>"
												+ value.name + "</h4>");
										result = result.concat("<p> Rp. "
												+ value.price.format(2)
												+ ".</p>");
										result = result.concat("</div>");
										result = result.concat("</div>");
									});
					result = result.concat("</div>");

					// clear array
					pages = [];
					// populate all contents into an array
					for (i = 1; i <= data.contents.totalPages; i++) {

						pagination = "<li id=\"pagination_" + (i - 1)
								+ "\" ><a onclick=\"fetchItemList(" + (i - 1)
								+ "," + pagingPageSize + ")\">" + i
								+ "</a></li>";

						pages.push(pagination);

					}

					for (i = 0; i < data.contents.totalPages; i++) {
						displayedpagination = displayedpagination
								.concat(pages[i]);
					}

					$('#item_list').html(result);

					if (data.contents.totalPages > 0) {
						$('#pagination').twbsPagination({
							totalPages : data.contents.totalPages,
							visiblePages : pagingNumber,
							onPageClick : function(event, page) {
								fetchItemList((page - 1), pagingPageSize);
							}
						});
					}

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

	$
			.ajax({
				url : '/' + servletContext + '/api/items/' + start + '/'
						+ limit + '/' + fType,
				method : "POST",
				contentType : 'application/json',
				dataType : 'json',
				data : JSON.stringify(filterData),
				success : function(data) {
					var result = "";
					var pagination = "";
					var displayedpagination = "";
					$
							.each(
									data.contents.content,
									function(index, value) {
										if (index == 0) {
											result = result
													.concat("<div class=\"row\" style=\"margin-bottom:25px\">");
										} else if (index % 4 == 0) {
											result = result.concat("</div>");
											result = result
													.concat("<div class=\"row\" style=\"margin-bottom:25px\">");
										}
										result = result
												.concat("<div class=\"col-sm-6 col-md-3\">");
										result = result.concat("<a href="
												+ value.url + ">");
										result = result
												.concat("<div style=\"box-shadow: -1px 7px 19px rgb(193, 193, 193);\">");
										result = result
												.concat("<img style=\"width: 100%;height: 250px;\" class=\"img-fluid\" src=\""
														+ value.image
																.split(',')[0]
														+ "\" alt=\"Responsive image\">");
										result = result.concat("</a>");
										result = result.concat("</div>");
										result = result
												.concat("<div class=\"caption\">");
										result = result.concat("<h4>"
												+ value.name + "</h4>");
										result = result.concat("<p> Rp."
												+ value.price.format(2)
												+ ".</p>");
										result = result.concat("</div>");
										result = result.concat("</div>");
									});
					result = result.concat("</div>");

					// clear array
					pages = [];
					// populate all contents into an array
					for (i = 1; i <= data.contents.totalPages; i++) {

						pagination = "<li id=\"pagination_" + (i - 1)
								+ "\" ><a onclick=\"fetchItemsSelectionList("
								+ (i - 1) + "," + pagingPageSize + ")\">" + i
								+ "</a></li>";

						pages.push(pagination);

					}

					for (i = 0; i < data.contents.totalPages; i++) {
						displayedpagination = displayedpagination
								.concat(pages[i]);
					}

					$('#item_list').html(result);

					if (destroy == true) {
						$('#pagination').twbsPagination('destroy');
					}

					if (data.contents.totalPages > 0) {
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
					}

				},
				error : function(jqXHR, textStatus, errorThrown) {
					console.log("error occured : " + errorThrown);
				}
			});

}

function searchButtonClick() {
	$('#search-header-button').click(function() {
		console.log("searc button clicked");
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

// vendor update submit form
function updateVendor() {
	$('#update_vendor_button').click(function() {
		$('#choosen_category_update_list option').each(function() {
			$(this).prop('selected', true);
		});

		$('#form_vendor_update').submit();
	});
}

function fetchFoundItemList() {
	var keyword = getUrlVars()["f"];
	var start = getUrlVars()["start"];
	var limit = getUrlVars()["limit"];
	var result = "";

	$.ajax({
		url : 'search?f=' + keyword + '&start=' + start + '&limit=' + limit,
		method : 'GET',
		success : function(data) {
			var result = $(data).find('#search-result');
			$('#search-result').html(result.children());
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error occured : " + errorThrown);
		}
	});
}

function loadContent(start, size) {

	var f = getUrlVars()["f"];
	if (history.pushState) {
		var newurl = window.location.protocol + "//" + window.location.host
				+ window.location.pathname + '?f=' + f + '&start=' + start
				+ '&limit=' + size;
		window.history.pushState({
			path : newurl
		}, '', newurl);
	}

	fetchFoundItemList();
}

function loadPagination() {

	var total = Number($('#total_search_page').text());
	var itemPerPage = Number($('#total_displayed_item').text());

	console.log('item per page : ' + itemPerPage);

	if (Number(total) > 0) {
		$('#search-pagination').twbsPagination(
				{
					totalPages : Number(total),
					visiblePages : pagingNumber,
					onPageClick : function(event, page) {
						loadContent((page - 1) * itemPerPage,
								((page - 1) * itemPerPage) + itemPerPage);

					}
				});
	}

}

function advanceSearch() {

	var eventType = $('#event-type-dropdown').val();
	var city = $('#city-dropdown').val();
	var groupSize = $('#capacity-dropdown').val();

	console.log('event type :' + eventType);
	console.log('city :' + city);
	console.log('capacity : ' + groupSize);

	var object = {
		"eventType" : eventType,
		"city" : city,
		"capacity" : groupSize
	}

	var service = 'advance-search';

	$("#search-result").load(
			service + " #search-item",
			object,
			function(data) {

				var total_search_page = Number($(data).find(
						'#total_search_page').text());
				var itemPerPage = Number($(data).find('#total_displayed_item')
						.text());

				if (total_search_page == 0) {
					total_search_page = 1;
				}

				console.log('total page :' + total_search_page);

				$('#search-pagination').twbsPagination('destroy');
				$('#search-pagination').twbsPagination(
						{
							totalPages : total_search_page,
							visiblePages : pagingNumber,
							onPageClick : function(event, page) {

								var start = (page - 1) * pagingPageSize;
								var limit = ((page - 1) * pagingPageSize)
										+ pagingPageSize;

								advanceSearchPagination(start, limit, object)

							}
						});

			});

}

function advanceSearchPagination(start, limit, criteria) {

	console.log('start=' + start + ',limit=' + limit);

	var service = 'advance-search'
			.concat('?start=' + start + '&limit=' + limit);

	$("#search-result").load(service + " #search-item", criteria);

}
