package com.wyl.comment.dao;

import com.wyl.comment.module.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by wangyulin on 20/01/2017.
 */
public interface CityRepository extends Repository<City, Long> {

    Page<City> findAll(Pageable pageable);
    Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name,String country, Pageable pageable);
    List<City> findTop1ByNameAndCountryAllIgnoringCase(String name, String country);
    List<City> findByNameAndCountryAllIgnoringCase(String name, String country);
}