package com.wyl.spring.boot.demo.task.job;

import com.wyl.spring.boot.demo.service.DemoService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Created by wangyulin on 24/03/2017.
 */
@Component("demoTask")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DemoTask extends Thread {

    final static Logger LOGGER= LoggerFactory.getLogger(DemoTask.class);

    @Resource(name = "demoService")
    private DemoService demoService;

    @Override
    public void run() {
        LOGGER.info("线程 : " + Thread.currentThread().getName() + "运行中....." + demoService);
        demoService.demo1("demoTask");
    }

}
