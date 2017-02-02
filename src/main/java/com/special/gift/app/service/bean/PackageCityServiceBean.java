package com.special.gift.app.service.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.special.gift.app.domain.City;
import com.special.gift.app.repository.PackageCityRepository;
import com.special.gift.app.service.PackageCityService;

@Service
public class PackageCityServiceBean implements PackageCityService {

  @Autowired
  private PackageCityRepository repository;

  @Override
  public List<City> findAll() {
    final List<City> list = new ArrayList<>();
    final Iterator<City> iter = repository.findAll().iterator();
    while (iter.hasNext()) {
      list.add(iter.next());
    }
    return list;
  }

}
