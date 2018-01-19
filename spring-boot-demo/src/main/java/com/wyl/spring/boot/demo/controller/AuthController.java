package com.wyl.spring.boot.demo.controller;

import com.wyl.spring.boot.demo.model.Auth;
import com.wyl.spring.boot.demo.repository.AuthRepository;
import com.wyl.spring.boot.demo.utils.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by wangyulin on 09/05/2017.
 */
@RestController
public class AuthController {

    final static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthRepository authRepository;

    @PostMapping(value = "/auth")
    public Auth saveAuth() {
        System.out.println("=====Begin save auth====");

        String filePath = "/Users/wangyulin/work/order_2017_04_22";
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());

            int counter = 0;
            for(String line : lines) {
                List<String> cols = null;
                try {
                    cols = DataUtils.patternMatching(line);
                    Auth auth = new Auth();
                    auth.setProductId(cols.get(3));
                    auth.setUserId(Long.valueOf(cols.get(10)));
                    auth.setProductType("THEME");
                    LOGGER.info("TAG_Auth auth : {}", auth);
                    authRepository.save(auth);
                    if(counter == 5) {
                        break;
                    }
                    counter ++;
                } catch (Exception e) {
                    System.out.println("Error Data : " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return authRepository.findOne(39099L);
    }

    @RequestMapping(name = "auth/{id}", method = RequestMethod.GET)
    public Auth getOne(@PathVariable long id) {
        return authRepository.findOne(id);
    }

}
