package com.wyl.comment.controller;

import com.wyl.comment.utils.SpringLoggingHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangyulin on 23/01/2017.
 */
@RestController
public class LoggerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/log")
    public String index(@RequestHeader(value="User-Agent") String userAgent, @RequestHeader HttpHeaders headers){
        System.out.println(headers);
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        new SpringLoggingHelper().helpMethod();
        return "index";
    }
}