package com.special.gift.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;

public interface ListingService {

  List<ItemListDto> findAllList(HttpServletRequest request, String category);

  List<ItemListDto> findAllList(HttpServletRequest request, String category, FilterDto filter);

}
