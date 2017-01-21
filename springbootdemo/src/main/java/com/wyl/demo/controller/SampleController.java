package com.wyl.demo.controller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.io.PrintStream;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        DispatcherServlet ds;
        AbstractAnnotationConfigDispatcherServletInitializer a;
        //WebConfig c;
        new PrintStream ( System.out ).println ("Hello World !");
        return "Hello World wyl!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
        //JpaRepository e;
        /*for(int i = 1; i< 100; i+=2) {
            if(i%4 == 3 && i%6==5 && i%8 == 7) {
                System.out.println (i);
            }
        }*/
    }
}