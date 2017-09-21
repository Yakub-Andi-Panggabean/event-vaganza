package com.special.gift.app.service.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.special.gift.app.util.CommonUtil;

@Service
public class ListingServiceBean implements ListingService {

  private static final Logger log = LoggerFactory.getLogger(ListingServiceBean.class);

  @Autowired
  private PackageVendorRepository pVendorRepository;

  @Autowired
  private PackageVenueRepository pVenueRepository;

  @Autowired
  private VendorRepository vendorRepository;


  @Value("${image.path.location}")
  private String imageRepoUrl;

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

        final Double discount = new Double(new Double(packages.getPackagePrice())
            * (packages.getDiscountRate() / new Double(100)));

        final Integer price = (packages.getPackagePrice()) - discount.intValue();

        final ItemListDto dto = new ItemListDto();
        final Vendor vendor = vendorRepository.findSingleVendorById(packages.getVendorId());

        final String image = packages.getPackageImg().substring(0,
            (packages.getPackageImg().length() > 0 ? packages.getPackageImg().length() - 1 : 0));

        dto.setCapacity(packages.getPackageCapacity());
        dto.setCategory(packages.getVendorDesc().getVendorType());
        dto.setDescription(packages.getPackageDesc());
        dto.setDiscountRate(packages.getDiscountRate());
        dto.setId(packages.getPackageId());
        dto.setImage(image);
        dto.setLocation(vendor != null ? vendor.getAddress() : "");
        dto.setMinimumPayment(packages.getMinimumPayment());
        dto.setName(packages.getPackageName());
        dto.setPackageType(CommonUtil.PACKAGE_VENDOR);
        dto.setPaxPrice(0);
        dto.setPrice(price);
        dto.setRentDuration(String.valueOf(packages.getTimePackage()));
        dto.setRoom("");
        dto.setUrl(new StringBuilder(request.getContextPath()).append("/packages/")
            .append(CommonUtil.PACKAGE_VENDOR).append("/").append(packages.getPackageId())
            .toString());
        dto.setVendorId(packages.getVendorId());
        dto.setVendorStyle(packages.getPackageStyle());
        dto.setUnit(packages.getUnit());

        // find vendor by vendor id
        items.add(dto);

      }

      // add package venue
      for (final PackageVenue venue : pVenueRepository.findAll()) {

        final ItemListDto dto = new ItemListDto();

        final Double discount = new Double(
            new Double(venue.getRentalPrice()) * (venue.getDiscountRate() / new Double(100)));

        final Integer price = (venue.getRentalPrice()) - discount.intValue();

        log.debug("discount for {} is {}", venue.getVenueName(), discount);

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
        dto.setPackageType(CommonUtil.PACKAGE_VENUE);
        dto.setPaxPrice(venue.getPaxPrice());

        dto.setPrice(price);

        dto.setRentDuration(venue.getTimeRent());
        dto.setRoom(venue.getVenueRoom());
        dto.setUrl(new StringBuilder(request.getContextPath()).append("/packages/")
            .append(CommonUtil.PACKAGE_VENUE).append("/").append(venue.getVenueId()).toString());
        dto.setVendorId(venue.getVendor());
        dto.setVendorStyle("");
        dto.setUnit(" Person");

        items.add(dto);

      }


      log.debug("total item count : {}", items.size());

      // filter per category
      if (category != null) {
        final List<ItemListDto> dtos = new ArrayList<>();
        dtos.clear();
        for (final ItemListDto itemListDto : items) {
          // if parent it will also contains all it's child
          if ((category.endsWith("0")
              && itemListDto.getCategory().substring(0, 2).equals(category.substring(0, 2)))
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

      log.debug("filter detail : {}", filter.toString());

      if ((filter.getVendorId() == null || filter.getVendorId().isEmpty())
          && (filter.getVenue() == null || filter.getVenue().isEmpty())
          && (filter.getParent() == null || filter.getParent().isEmpty())
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

          boolean isParentValid = true;
          boolean isIdValid = true;
          boolean isCapacityValid = true;
          boolean isCityValid = true;
          boolean isKeywordValid = true;
          boolean isBetweenPrice = true;
          boolean isMaxPriceValid = true;
          boolean isMinPriceValid = true;
          boolean isPackageTypeValid = true;
          boolean isCategoryValid = true;
          boolean isVenueValid = true;
          boolean isVendorValid = true;

          if (filter.getParent() != null) {
            isParentValid = filter.getParent().length() > 2
                && (filter.getParent().substring(0, 2).equals(item.getCategory().substring(0, 2)));
          }

          // filter id
          if (filter.getId() != null) {

            isIdValid = filter.getId().equalsIgnoreCase(item.getId());
          }

          // filter capacity
          if (filter.getCapacity() != null && filter.getCapacity() > 0) {
            // log.debug("filtering capacity : {}", filter.getCapacity());
            isCapacityValid = item.getCapacity() == filter.getCapacity();
          }

          log.debug("item location : {}", item.getLocation());

          // filter city
          if (filter.getCity() != null && item.getLocation() != null) {

            log.debug("filtering city : {}", filter.getCity());

            isCityValid = (item.getLocation().equalsIgnoreCase(filter.getCity())
                || item.getLocation().toLowerCase().contains(filter.getCity().toLowerCase()));

          }

          if (filter.getKeyword() != null && !filter.getKeyword().isEmpty()) {
            isKeywordValid =
                item.getName().toLowerCase().contains(filter.getKeyword().toLowerCase());
          }

          if ((filter.getMaxPrice() != null && filter.getMaxPrice() > 0
              && filter.getMinPrice() != null && filter.getMinPrice() > 0)) {

            isBetweenPrice = (item.getPrice() <= filter.getMaxPrice()
                && item.getPrice() >= filter.getMinPrice());
          }

          // max prize
          if (filter.getMaxPrice() != null && filter.getMaxPrice() > 0
              && filter.getMinPrice() == 0) {
            isMaxPriceValid = item.getPrice() <= filter.getMaxPrice();
          }

          if (filter.getMinPrice() != null && filter.getMinPrice() > 0
              && filter.getMaxPrice() == 0) {
            isMinPriceValid = item.getPrice() >= filter.getMinPrice();
          }

          if (filter.getPackageType() != null && !filter.getPackageType().isEmpty()
              && !filter.getPackageType().toLowerCase().equals("all")) {

            isPackageTypeValid = filter.getPackageType().equalsIgnoreCase(item.getPackageType());
          }

          if (filter.getCategory() != null && !filter.getCategory().isEmpty()) {


            final boolean detailedChild = filter.getCategory().equalsIgnoreCase(item.getCategory());
            final boolean parent = filter.getCategory().substring(0, 2)
                .equalsIgnoreCase(item.getCategory().substring(0, 2));

            if (filter.getCategory().length() > 2
                && String.valueOf(filter.getCategory().charAt(2)).equals("0")) {
              isCategoryValid = parent;
            } else {
              isCategoryValid = detailedChild;
            }

          }

          if (filter.getVenue() != null && !filter.getVenue().isEmpty()
              && item.getPackageType().equals(CommonUtil.PACKAGE_VENDOR)) {

            final Vendor vendor = vendorRepository.findSingleVendorById(item.getVendorId());

            isVenueValid = vendor != null && vendor.getVenueVendor().equals(filter.getVenue());

          }


          // filter package based on vendor venue
          if (filter.getVendorId() != null && !filter.getVendorId().isEmpty()) {

            // log.debug("filtering vendor id : {},item vendor id:{}", filter.getVendorId(),
            // item.getVendorId());

            final Vendor vendor = vendorRepository.findSingleVendorById(item.getVendorId());

            isVendorValid = filter.getVendorId().equalsIgnoreCase(vendor.getVenueVendor())
                || filter.getVendorId().equals(item.getVendorId());
          }


          final boolean isItemValid = isParentValid && isIdValid && isCapacityValid && isCityValid
              && isKeywordValid && isBetweenPrice && isMaxPriceValid && isMinPriceValid
              && isPackageTypeValid && isCategoryValid && isVenueValid && isVendorValid;

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

  /**
   *
   * find package data based on venue and category
   *
   */
  @Override
  public List<ItemListDto> findListByCategoryAndVenue(HttpServletRequest request, String category,
      String venue, String city, String capacity) throws Exception {

    final List<ItemListDto> items = new ArrayList<>();

    ItemListDto dto;
    try {

      for (final PackageVendor packages : pVendorRepository.findPackageByCategoryAndVenue(category,
          venue, city)) {

        dto = new ItemListDto();

        final Double discount = new Double(new Double(packages.getPackagePrice())
            * (packages.getDiscountRate() / new Double(100)));

        final Integer price = (packages.getPackagePrice()) - discount.intValue();

        final Vendor vendor = vendorRepository.findSingleVendorById(packages.getVendorId());

        final String image = packages.getPackageImg().substring(0,
            (packages.getPackageImg().length() > 0 ? packages.getPackageImg().length() - 1 : 0));

        dto.setCapacity(packages.getPackageCapacity());
        dto.setCategory(packages.getVendorDesc().getVendorType());
        dto.setDescription(packages.getPackageDesc());
        dto.setDiscountRate(packages.getDiscountRate());
        dto.setId(packages.getPackageId());
        dto.setImage(image);
        dto.setLocation(vendor != null ? vendor.getAddress() : "");
        dto.setMinimumPayment(packages.getMinimumPayment());
        dto.setName(packages.getPackageName());
        dto.setPackageType(CommonUtil.PACKAGE_VENDOR);
        dto.setPaxPrice(0);
        dto.setPrice(price);
        dto.setRentDuration(String.valueOf(packages.getTimePackage()));
        dto.setRoom("");
        dto.setUrl(new StringBuilder(request.getContextPath()).append("/packages/")
            .append(CommonUtil.PACKAGE_VENDOR).append("/").append(packages.getPackageId())
            .toString());
        dto.setVendorId(packages.getVendorId());
        dto.setVendorStyle(packages.getPackageStyle());


        // more than
        if (capacity.contains(">")) {

          if (packages.getPackageCapacity() > Integer.parseInt(capacity.split(",")[1])) {
            items.add(dto);
          }

        } else {// less than

          if (packages.getPackageCapacity() < Integer.parseInt(capacity.split(",")[1])) {
            items.add(dto);
          }

        }

        // find vendor by vendor id


      }

    } catch (final Exception ex) {

      throw ex;

    }

    return items;
  }



}
