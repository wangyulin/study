package com.wyl.spring.boot.demo.task.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangyulin on 25/03/2017.
 */
@Component
public class ScheduledTasks {
    final static Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);
    final static Logger HELLO_LOGGER = LoggerFactory.getLogger("hello");

    private final AtomicInteger counter = new AtomicInteger();

    @Autowired
    private AsyncWorker worker;

    @Scheduled(fixedRate = 10 * 1000, initialDelay = 1 * 1000)
    public void report1() {
        HELLO_LOGGER.info("Hello logger ");
        for (int i = 0; i < 1; i++) {
            worker.work("reportCurrentTime1 - " + counter.incrementAndGet());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //@Scheduled(fixedRate = 5000, initialDelay = 10 * 1000)
    public void report2() {
        for (int i = 0; i < 1; i++) {
            worker.work("reportCurrentTime2 - " + counter.incrementAndGet());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
