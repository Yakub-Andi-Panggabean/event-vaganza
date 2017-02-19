package com.special.gift.app.service.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.special.gift.app.domain.PackageVenue;
import com.special.gift.app.repository.PackageVenueRepository;
import com.special.gift.app.service.VenueService;

@Service
public class PackageVenueServiceBean implements VenueService {

  @Autowired
  private PackageVenueRepository repository;

  @Override
  public Page<PackageVenue> findAll() {

    final List<PackageVenue> venues = new ArrayList<>();
    final Iterator<PackageVenue> iter = repository.findAll().iterator();
    while (iter.hasNext()) {
      venues.add(iter.next());
    }
    final Page<PackageVenue> pages = new PageImpl<>(venues);
    return pages;
  }

  @Override
  public PackageVenue findVenue(String venueId) {
    // TODO Auto-generated method stub
    return repository.findOne(venueId);
  }

}
