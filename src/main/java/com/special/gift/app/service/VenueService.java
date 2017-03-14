package com.special.gift.app.service;

import org.springframework.data.domain.Page;

import com.special.gift.app.domain.PackageVenue;

public interface VenueService {

  Page<PackageVenue> findAll();

  PackageVenue findVenue(String venueId);

  PackageVenue findByVendorId(String id);

}
