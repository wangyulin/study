package com.wyl.spring.boot.demo.controller;

import com.wyl.spring.boot.demo.model.User;
import com.wyl.spring.boot.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangyulin on 24/03/2017.
 */
@RestController
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/home")
    public String home() {
        return "welcome !";
    }

    @PostMapping(value = "/user")
    public String saveUser() {
        User u = new User();
        u.setName("wyl");
        u.setEmail("wyl@wyl.com");
        userRepository.save(u);
        return "success";
    }

}
