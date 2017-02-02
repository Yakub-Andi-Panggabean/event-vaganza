package com.special.gift.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.special.gift.app.domain.City;

public interface PackageCityRepository extends CrudRepository<City, Integer> {

  Page<City> findAll(Pageable pageable);

}
