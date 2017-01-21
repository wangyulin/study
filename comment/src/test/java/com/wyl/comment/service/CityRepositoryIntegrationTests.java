package com.wyl.comment.service;

import com.wyl.comment.dao.CityRepository;
import com.wyl.comment.module.City;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wangyulin on 20/01/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryIntegrationTests {

    @Autowired
    CityRepository repository;

    @Test
    public void findsFirstPageOfCities() {

        Page<City> cities = this.repository.findAll(new PageRequest(0, 10));
        assertThat(cities.getTotalElements()).isGreaterThan(20L);
    }
}
