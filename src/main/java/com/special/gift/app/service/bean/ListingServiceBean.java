package com.special.gift.app.service.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.special.gift.app.domain.PackageVendor;
import com.special.gift.app.domain.PackageVenue;
import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;
import com.special.gift.app.repository.PackageVendorRepository;
import com.special.gift.app.repository.PackageVenueRepository;
import com.special.gift.app.service.ListingService;
import com.special.gift.app.util.CommonUtil;

@Service
public class ListingServiceBean implements ListingService {

  private static final Logger log = LoggerFactory.getLogger(ListingServiceBean.class);

  @Autowired
  private PackageVendorRepository pVendorRepository;

  @Autowired
  private PackageVenueRepository pVenueRepository;

  private static final String PACKAGE_VENUE = "venues";
  private static final String PACKAGE_VENDOR = "vendors";

  @Override
  public List<ItemListDto> findAllList(HttpServletRequest request, String category)
      throws Exception {

    final List<ItemListDto> items = new ArrayList<>();

    try {

      log.debug("servlet context : {}", request.getContextPath());

      // add package vendor
      for (final PackageVendor packages : pVendorRepository.findAll()) {

        // add package vendor
        items.add(new ItemListDto(packages.getPackageId(), packages.getPackageName(),
            PACKAGE_VENDOR, packages.getVendor().getVendorId().getType(), packages.getPackageImg(),
            new StringBuilder(request.getContextPath()).append("/packages/").append(PACKAGE_VENDOR)
                .append("/").append(packages.getPackageId()).toString(),
            CommonUtil.convertIntoCurrencyFormat(packages.getPackagePrice()),
            packages.getPackagePrice(), packages.getPackageCapacity(), null,
            packages.getDiscountRate(), packages.getMinimumPayment()));

      }

      // add package venue
      for (final PackageVenue venue : pVenueRepository.findAll()) {
        items.add(new ItemListDto(venue.getVenueId(), venue.getVenueRoom(), PACKAGE_VENUE,
            venue.getVendor().getVendorId().getType(), venue.getVenuePortofolio(),
            new StringBuilder("/packages/").append(PACKAGE_VENUE).append("/")
                .append(venue.getVenueId()).toString(),
            CommonUtil.convertIntoCurrencyFormat(venue.getRentalPrice()), venue.getRentalPrice(),
            Integer.valueOf(venue.getRoomCapacity()), venue.getCity(), venue.getDiscountRate(),
            venue.getMinimumPayment()));
      }


      log.debug("total item count : {}", items.size());

      if (category != null) {
        final List<ItemListDto> dtos = new ArrayList<>();
        dtos.clear();
        for (final ItemListDto itemListDto : items) {
          if (itemListDto.getCategory().equals(category)) {
            dtos.add(itemListDto);
          }
        }



        log.debug("total filtered item count : {}", dtos.size());

        return dtos;
      }



    } catch (final Exception exception) {
      exception.printStackTrace();
      throw exception;
    }

    return items;
  }

  @Override
  public List<ItemListDto> findAllList(HttpServletRequest request, String category,
      final FilterDto filter) throws Exception {
    final List<ItemListDto> list = findAllList(request, category);
    final List<ItemListDto> filteredList = new ArrayList<>();
    try {

      for (final ItemListDto item : list) {

        // filter capacity
        if (filter.getCapacity() != null && item.getCapacity() == filter.getCapacity()) {
          filteredList.add(item);
        }

        // filter city
        if (filter.getCity() != null && item.getLocation() != null
            && item.getLocation().equalsIgnoreCase(filter.getCity())) {
          filteredList.add(item);
        }

        if (filter.getKeyword() != null
            && item.getName().toLowerCase().contains(filter.getKeyword().toLowerCase())) {
          filteredList.add(item);
        }

        if ((filter.getMaxPrice() != null && filter.getMinPrice() != null)
            && (item.getIntPrice() <= filter.getMaxPrice()
                && item.getIntPrice() >= filter.getMinPrice())) {
          filteredList.add(item);
        }

        if (filter.getMaxPrice() != null && filter.getMaxPrice() <= item.getIntPrice()) {
          filteredList.add(item);
        }

        if (filter.getMinPrice() != null && filter.getMinPrice() >= item.getIntPrice()) {
          filteredList.add(item);
        }

        if (filter.getPackageType() != null
            && filter.getPackageType().equals(item.getPackageType())) {
          filteredList.add(item);
        }

      }

      if (filter.getSorting() != null) {

        if (filter.getSorting().getPropertyName().equals("capacity")) {
          Collections.sort(filteredList, new Comparator<ItemListDto>() {

            @Override
            public int compare(ItemListDto o1, ItemListDto o2) {
              if (filter.getSorting().getOrder().equals("asc")) {
                return o1.getCapacity().compareTo(o2.getCapacity());
              } else {
                return o2.getCapacity().compareTo(o1.getCapacity());
              }
            }

          });
        }


        if (filter.getSorting().getPropertyName().equals("price")) {
          Collections.sort(filteredList, new Comparator<ItemListDto>() {

            @Override
            public int compare(ItemListDto o1, ItemListDto o2) {
              if (filter.getSorting().getOrder().equals("asc")) {
                return o1.getIntPrice().compareTo(o2.getIntPrice());
              } else {
                return o2.getIntPrice().compareTo(o1.getIntPrice());
              }
            }
          });
        }


        if (filter.getSorting().getPropertyName().equals("discount")) {
          Collections.sort(filteredList, new Comparator<ItemListDto>() {

            @Override
            public int compare(ItemListDto o1, ItemListDto o2) {
              if (filter.getSorting().getOrder().equals("asc")) {
                return o1.getDiscountRate().compareTo(o2.getDiscountRate());
              } else {
                return o2.getDiscountRate().compareTo(o1.getDiscountRate());
              }
            }
          });
        }

      }

    } catch (final Exception exception) {
      exception.printStackTrace();
      throw exception;
    }

    return filteredList;
  }



}
