package com.wyl.spring.boot.demo.task.schedule;

import com.wyl.spring.boot.demo.model.User;
import com.wyl.spring.boot.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by wangyulin on 25/03/2017.
 */
@Component
public class AsyncWorker{
    final static Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    @Async
    public void work(String name) {
        String threadName = Thread.currentThread().getName();
        LOGGER.info("{} beginning work on {}", new Object[]{threadName, name});
        //System.out.println("   " + threadName + " beginning work on " + name);
        try {

            Thread.sleep(2000); // simulates work

        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        //System.out.println("   " + threadName + " completed work on " + name);
        LOGGER.info("{} completed work on {}", new Object[]{threadName, name});
    }
}