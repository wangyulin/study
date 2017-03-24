package com.wyl.spring.boot.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangyulin on 24/03/2017.
 */
@RestController
public class HomeController {


    @GetMapping(value = "/home")
    public String home() {
        return "welcome !";
    }

}
