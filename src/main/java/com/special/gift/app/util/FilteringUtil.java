package com.special.gift.app.util;

import java.util.List;

import com.google.common.base.Predicate;
import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;

public class FilteringUtil {

  Predicate<ItemListDto> generatePredicateIteam(final FilterDto filter) {
    final Predicate<ItemListDto> predicate = new Predicate<ItemListDto>() {

      @Override
      public boolean apply(ItemListDto input) {

        if (filter.getCapacity() != null && filter.getCapacity() > 0) {
          return input.getCapacity() == filter.getCapacity();
        }


        return false;
      }
    };



    return predicate;

  }

  public static List<ItemListDto> fiteringList(List<ItemListDto> items, FilterDto filter) {

    // final Collection<ItemListDto> colleciton = Collections2.filter(items, coll);

    return null;
  }

}
