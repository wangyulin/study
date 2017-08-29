package com.wyl.spring.boot.demo.controller;

import com.wyl.spring.boot.demo.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangyulin on 09/05/2017.
 */
@RestController
public class AuthController {

    @Autowired
    private AuthRepository authRepository;

    @PostMapping(value = "/auth")
    public String saveAuth() {
        System.out.println("=====Begin save auth====");

        
        return "success";
    }

}
