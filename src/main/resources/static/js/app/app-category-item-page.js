/**
 * 
 */


function initCategoryPage(){
		
	filteringKeyword();
	filteringPackageType();
	filteringPrice();
	filteringPackageType();
	sortingPackageList();
	loadCategoryPagination();
	renderCategoryFinderCity();
	filteringPackageCitySelection();

}


function filteringKeyword() {

	$('#filter_keyword_items').keypress(function(e) {
		if (e.which === 13) {
			fetchItemsSelectionList(0, pagingPageSize);
		}
	});

}


function filteringPrice() {

	$('#filter_min_price_selection').keypress(function(e) {
		if (e.which === 13) {
			fetchItemsSelectionList(0, pagingPageSize);
		}
	});
	
	$('#filter_max_price_selection').keypress(function(e) {
		if (e.which === 13) {
			fetchItemsSelectionList(0, pagingPageSize);
		}
	});

}

function filteringPackageCitySelection() {
  $('#filter_city_selection').change(function(e) {
    fetchItemsSelectionList(0, pagingPageSize);
  });
}

function filteringPackageType() {
	$('#filter_package_type_selection').change(function(e) {
		fetchItemsSelectionList(0, pagingPageSize);
	});
}

function sortingPackageList() {
	$('#filtering_package_sorting').change(function(e) {
		fetchItemsSelectionList(0, pagingPageSize);
	});
}


function fetchItemsSelectionList(start, limit){
	var promiseFetchCategoryItem=promiseFetchItemsSelectionList(start, limit);
	
	promiseFetchCategoryItem.done(function(data){
		
		var fType = getUrlVars()["c"];
		var result = $(data).find('#item_list');
		var totalCategoryPage=$(data).find('#total_category_page').text();
		var totalDisplayeItemCategory=$(data).find('#total_displayed_item_category');
		
		$('#item_list').html(result.children());
		
		console.log(result.children());
		
		$('#category-pagination').twbsPagination('destroy');
		
		$('#category-pagination').twbsPagination({
			
			totalPages : totalCategoryPage,
			visiblePages : pagingNumber,
			onPageClick : function(event, page) {
					
//				if(page>1){
					
					var nextStart = (page - 1) * pagingPageSize;
					var nextLimit = ((page - 1) * pagingPageSize)
							+ pagingPageSize;
					
					//change url
					var newurl = window.location.protocol + "//" + window.location.host
					+ window.location.pathname + '?c=' + fType + '&start=' + nextStart
					+ '&limit=' + nextLimit;
					
					if (history.pushState) {
						window.history.pushState({
							path : newurl
						}, '', newurl);
					}
				
					
					//load item based on pagination
					$('#item_list').html($('#spinner-progress').html());
					promiseFetchItemsSelectionList(nextStart,nextLimit).done(function(data){
						
						
						var paginatedChildren=$(data).find('#item_list').children();
						
						$('#item_list').html($(data).find('#item_list').children());
					
					});
					
					
				}
				
//			}
			
		});
		
		
		
	});
	
	promiseFetchCategoryItem.fail(function(jqXHR, textStatus, errorThrown){
		console.log("error occured : " + errorThrown);
	});
}

/**
 * 
 * filter data in category page
 * 
 * @param start
 * @param limit
 * @returns
 */
function promiseFetchItemsSelectionList(start, limit) {
	
	var fType = getUrlVars()["c"];
	var filterKeyword = $('#filter_keyword_items').val();
	var filterMinPrice = $('#filter_min_price_selection').val();
	var filterMaxPrice = $('#filter_max_price_selection').val();
	var filterCapacity = $('#filter_capacity_selection').val();
	var filterCity = $('#filter_city_selection').val();
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
	} else if (packageSorting === 'lowest_capacity') {
		filterSorting = {
			propertyName : "capacity",
			order : "asc"
		}
	}else if(packageSorting === 'highest_capacity'){
		filterSorting = {
				propertyName : "capacity",
				order : "desc"
		}
	} else {
		packageSorting = null;
	}

	filterData.sorting = filterSorting;
	
	var loading = $('#spinner-progress').html();
	
	$('#item_list').html(loading);
	
	return $.ajax({
		url : servletContext + 'advance-find-categories?c=' + fType
			+ '&start=' + start + '&limit=' + limit,
		method : "POST",
		contentType : 'application/json',
		data : JSON.stringify(filterData)
	});
	 
}


function loadCategoryPagination() {

	var total = Number($('#total_category_page').text());
	var itemPerPage = Number($('#total_displayed_item_category').text());

	if (Number(total) > 0) {
		$('#category-pagination').twbsPagination(
				{
					totalPages : Number(total),
					visiblePages : pagingNumber,
					onPageClick : function(event, page) {

						loadContentByCategory((page - 1) * itemPerPage,
								((page - 1) * itemPerPage) + itemPerPage);

					}
				});
	}

}


function renderCategoryFinderCity(){
  findExistingCity().then(function(data){
    
    var options="<option></option>";
    
    $.each(data,function(index,value){
      //console.log(value);
      options=options.concat("<option value='"+value.name+"'>").concat(value.description).concat("</option>");
    });
    
    $("#filter_city_selection").html(options);
    
  },function(jqXHR, textStatus, errorThrown){
    console.log(errorThrown);
  });
}


