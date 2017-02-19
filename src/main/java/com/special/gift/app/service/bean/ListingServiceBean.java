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
import com.special.gift.app.domain.Vendor;
import com.special.gift.app.dto.FilterDto;
import com.special.gift.app.dto.ItemListDto;
import com.special.gift.app.repository.PackageVendorRepository;
import com.special.gift.app.repository.PackageVenueRepository;
import com.special.gift.app.repository.VendorRepository;
import com.special.gift.app.service.ListingService;

@Service
public class ListingServiceBean implements ListingService {

  private static final Logger log = LoggerFactory.getLogger(ListingServiceBean.class);

  @Autowired
  private PackageVendorRepository pVendorRepository;

  @Autowired
  private PackageVenueRepository pVenueRepository;

  @Autowired
  private VendorRepository vendorRepository;

  private static final String PACKAGE_VENUE = "venue";
  private static final String PACKAGE_VENDOR = "vendor";

  /*
   * (non-Javadoc)
   *
   * @see
   * com.special.gift.app.service.ListingService#findAllList(javax.servlet.http.HttpServletRequest,
   * java.lang.String)
   */
  @Override
  public List<ItemListDto> findAllList(HttpServletRequest request, String category)
      throws Exception {

    final List<ItemListDto> items = new ArrayList<>();

    try {

      log.debug("servlet context : {}", request.getContextPath());

      // add package vendor
      for (final PackageVendor packages : pVendorRepository.findAll()) {

        final ItemListDto dto = new ItemListDto();
        final Vendor vendor = vendorRepository.findSingleVendorById(packages.getVendorId());

        dto.setCapacity(packages.getPackageCapacity());
        dto.setCategory(packages.getVendorDesc().getVendorType());
        dto.setDescription(packages.getPackageDesc());
        dto.setDiscountRate(packages.getDiscountRate());
        dto.setId(packages.getPackageId());
        dto.setImage(packages.getPackageImg().substring(0,
            (packages.getPackageImg().length() > 0 ? packages.getPackageImg().length() - 1 : 0)));
        dto.setLocation(vendor.getAddress());
        dto.setMinimumPayment(packages.getMinimumPayment());
        dto.setName(packages.getPackageName());
        dto.setPackageType(PACKAGE_VENDOR);
        dto.setPaxPrice(0);
        dto.setPrice(packages.getPackagePrice());
        dto.setRentDuration(String.valueOf(packages.getTimePackage()));
        dto.setRoom("");
        dto.setUrl(new StringBuilder(request.getContextPath()).append("/packages/")
            .append(PACKAGE_VENDOR).append("/").append(packages.getPackageId()).toString());
        dto.setVendorId(packages.getVendorId());
        dto.setVendorStyle(packages.getPackageStyle());

        // find vendor by vendor id
        items.add(dto);

      }

      // add package venue
      for (final PackageVenue venue : pVenueRepository.findAll()) {

        final ItemListDto dto = new ItemListDto();

        dto.setCapacity(Integer.parseInt(venue.getRoomCapacity()));
        dto.setCategory(venue.getVendorDesc().getVendorType());
        dto.setDescription(venue.getVenuePackage());
        dto.setDiscountRate(venue.getDiscountRate());
        dto.setId(venue.getVenueId());
        dto.setImage(venue.getVenuePortofolio());
        dto.setLocation(new StringBuilder(venue.getVenueAddress()).append(",")
            .append(venue.getCity()).toString());
        dto.setMinimumPayment(venue.getMinimumPayment());
        dto.setName(venue.getVenueName());
        dto.setPackageType(PACKAGE_VENUE);
        dto.setPaxPrice(venue.getPaxPrice());
        dto.setPrice(venue.getRentalPrice());
        dto.setRentDuration(venue.getTimeRent());
        dto.setRoom(venue.getVenueRoom());
        dto.setUrl(new StringBuilder(request.getContextPath()).append("/packages/")
            .append(PACKAGE_VENUE).append("/").append(venue.getVenueId()).toString());
        dto.setVendorId(venue.getVendor());
        dto.setVendorStyle("");

        items.add(dto);

      }


      log.debug("total item count : {}", items.size());

      if (category != null) {
        final List<ItemListDto> dtos = new ArrayList<>();
        dtos.clear();
        for (final ItemListDto itemListDto : items) {
          // if parent it will also contains all it's child
          if ((category.endsWith("0")
              && itemListDto.getCategory().substring(1, 2).equals(category.substring(1, 2)))
              || (itemListDto.getCategory().equals(category))) {
            dtos.add(itemListDto);
          }
        }



        log.debug("total filtered item count with category {} : {}", category, dtos.size());

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

      log.debug("filter : {}", filter.toString());

      if ((filter.getParent() == null || filter.getParent().isEmpty())
          && (filter.getCategory() == null || filter.getCategory().isEmpty()
              || filter.getCategory().equals(""))
          && (filter.getCapacity() == null || filter.getCapacity() == 0)
          && (filter.getCity() == null || filter.getCity().isEmpty() || filter.getCity().equals(""))
          && (filter.getKeyword() == null || filter.getKeyword().isEmpty()
              || filter.getKeyword().equals(""))
          && (filter.getMaxPrice() == null || filter.getMaxPrice() == 0)
          && (filter.getMinPrice() == null || filter.getMinPrice() == 0)
          && (filter.getId() == null || filter.getId().isEmpty() || filter.getId().equals(""))
          && (filter.getPackageType() == null || filter.getPackageType().isEmpty()
              || filter.getPackageType().toLowerCase().equals("all"))) {
        log.debug("no filtering");
        filteredList.addAll(list);
      } else {

        log.debug("filtering processed");

        for (final ItemListDto item : list) {

          boolean isItemValid = false;

          if (filter.getParent() != null) {
            isItemValid = filter.getParent().length() > 2
                && (filter.getParent().substring(0, 2).equals(item.getCategory().substring(0, 2)));
          }

          // filter id
          if (filter.getId() != null) {
            // log.debug("filtering id : {}", filter.getId());
            // log.debug("item filtered : {}", item.toString());
            // filteredList.add(item);
            isItemValid = filter.getId().equalsIgnoreCase(item.getId());
          }

          // filter capacity
          if (filter.getCapacity() != null && filter.getCapacity() > 0) {
            // log.debug("filtering capacity : {}", filter.getCapacity());
            isItemValid = item.getCapacity() == filter.getCapacity();
          }

          // filter city
          if (filter.getCity() != null && !filter.getCity().isEmpty() && item.getLocation() != null
              && !item.getLocation().isEmpty()) {
            // log.debug("filtering city : {}", filter.getCity());
            isItemValid = (item.getLocation().equalsIgnoreCase(filter.getCity())
                || item.getLocation().toLowerCase().contains(filter.getCity().toLowerCase()));
          }

          if (filter.getKeyword() != null && !filter.getKeyword().isEmpty()) {
            // log.debug("filtering keyword : {}", filter.getKeyword());
            isItemValid = item.getName().toLowerCase().contains(filter.getKeyword().toLowerCase());
          }

          if ((filter.getMaxPrice() != null && filter.getMaxPrice() > 0
              && filter.getMinPrice() != null && filter.getMinPrice() > 0)) {
            // log.debug("filtering min price : {} and max price : {}", filter.getMinPrice(),
            // filter.getMaxPrice());
            isItemValid = (item.getPrice() <= filter.getMaxPrice()
                && item.getPrice() >= filter.getMinPrice());
          }

          // max prize
          if (filter.getMaxPrice() != null && filter.getMaxPrice() > 0
              && filter.getMinPrice() == 0) {
            // log.debug("filtering max price : {}", filter.getMaxPrice());
            // log.debug("item price : {}", item.getPrice());
            isItemValid = item.getPrice() <= filter.getMaxPrice();
          }

          if (filter.getMinPrice() != null && filter.getMinPrice() > 0
              && filter.getMaxPrice() == 0) {
            // log.debug("filtering min price : {}", filter.getMinPrice());
            isItemValid = item.getPrice() >= filter.getMinPrice();
          }

          if (filter.getPackageType() != null && !filter.getPackageType().isEmpty()
              && !filter.getPackageType().toLowerCase().equals("all")) {
            // log.debug("filtering package type : {},item type:{}", filter.getPackageType(),
            // item.getPackageType());

            isItemValid = filter.getPackageType().equalsIgnoreCase(item.getPackageType());
          }

          if (filter.getCategory() != null && !filter.getCategory().isEmpty()) {

            // log.debug("filtering filter category : {}", filter.getCategory());
            // log.debug("filtering item category : {}", item.getCategory());

            final boolean detailedChild = filter.getCategory().equalsIgnoreCase(item.getCategory());
            final boolean parent = filter.getCategory().substring(0, 2)
                .equalsIgnoreCase(item.getCategory().substring(0, 2));

            isItemValid = detailedChild || parent;

            if (filter.getCategory().length() > 2
                && String.valueOf(filter.getCategory().charAt(2)).equals("0")) {
              isItemValid = parent;
            } else {
              isItemValid = detailedChild;
            }

          }

          if (isItemValid) {
            filteredList.add(item);
          }

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

          log.debug("filtered size : {}", filteredList.size());

          Collections.sort(filteredList, new Comparator<ItemListDto>() {

            @Override
            public int compare(ItemListDto o1, ItemListDto o2) {
              if (filter.getSorting().getOrder().equals("asc")) {
                return o1.getPrice().compareTo(o2.getPrice());
              } else {
                return o2.getPrice().compareTo(o1.getPrice());
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
