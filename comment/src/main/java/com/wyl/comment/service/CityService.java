package com.wyl.comment.service;

import com.wyl.comment.dao.CitySearchCriteria;
import com.wyl.comment.module.City;
import com.wyl.comment.module.HotelSummary;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by wangyulin on 20/01/2017.
 */
public interface CityService {

    Page<City> findCities(CitySearchCriteria criteria, Pageable pageable);

    City getCity(String name, String country);
    List<City> getCitys(String name, String country);

    Page<HotelSummary> getHotels(City city, Pageable pageable);

}
