package com.wyl.comment.controller;

import com.wyl.comment.module.City;
import com.wyl.comment.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangyulin on 20/01/2017.
 */
@RestController
public class SampleController {

    @Autowired
    private CityService cityService;

    @GetMapping("/")
    @Transactional(readOnly = true)
    public String helloWorld() {
        return this.cityService.getCity("Bath", "UK").getName();
    }

    @GetMapping("/json")
    @Transactional(readOnly = true)
    public City helloWorldCity() {
        return this.cityService.getCity("Bath", "UK");
    }

    @GetMapping("/list")
    @Transactional(readOnly = true)
    public List<City> citys() {
        return this.cityService.getCitys("Bath", "UK");
    }

    @PostMapping("city")
    public City addCity(@RequestBody City city) {
        System.out.println(city.getName());
        return city;
    }

}
