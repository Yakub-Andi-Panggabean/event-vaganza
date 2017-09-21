/**
 * 
 * search page
 * 
 */

function initSearchPage(){
	loadPagination();
}


function advanceSearch() {

	var eventType = $('#event-type-dropdown').val();
	var city = $('#city-dropdown').val();
	var groupSize = $('#capacity-dropdown').val();

	var object = {
		"eventType" : eventType,
		"city" : city,
		"capacity" : groupSize
	}

	var service = 'advance-search';

	var loading = $('#spinner-progress').html();
	
	$('#search-result').html(loading);

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

	var service = 'advance-search'
			.concat('?start=' + start + '&limit=' + limit);
	
	var loading = $('#spinner-progress').html();
	
	$('#search-result').html(loading);
	
	$("#search-result").load(service + " #search-item", criteria);

}

function fetchFoundItemList() {
	var keyword = getUrlVars()["f"];
	var start = getUrlVars()["start"];
	var limit = getUrlVars()["limit"];
	var result = "";

	var progress = $('#spinner-progress').html();

	$('#search-result').html(progress);

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