package com.wyl.spring.boot.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by wangyulin on 24/03/2017.
 */
@Service
public class DemoService {
    final static Logger LOGGER= LoggerFactory.getLogger(DemoService.class);

    public String demo1(String jobName) {
        LOGGER.info("{} service执行成功 !", jobName);
        return jobName + "执行service成功 ！！！";
    }

}
