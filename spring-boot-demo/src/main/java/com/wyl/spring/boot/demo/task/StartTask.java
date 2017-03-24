package com.wyl.spring.boot.demo.task;

import com.wyl.spring.boot.demo.conf.ApplicationContextProvider;
import com.wyl.spring.boot.demo.task.job.DemoTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by wangyulin on 24/03/2017.
 */
@Component
public class StartTask {

    final static Logger LOGGER = LoggerFactory.getLogger(StartTask.class);

    @PostConstruct
    public void init(){
        DemoTask demoTask = ApplicationContextProvider.getBean("demoTask", DemoTask.class);
        demoTask.start();

        /*DemoTask demoTask1 = ApplicationContextProvider.getBean("demoTask", DemoTask.class);
        demoTask1.start();*/
    }
}
