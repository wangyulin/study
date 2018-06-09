package com.wyl.java7concurrency.executor.demo1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 23/08/2017.
 */
public class Task implements Runnable {

    private Date initDate;
    private String name;

    public Task(String name) {
        this.initDate = new Date();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Task %s: Created on: %s\n",
                Thread.currentThread().getName(), name, initDate);
        System.out.printf("%s: Task %s: Started on: %s\n",
                Thread.currentThread().getName(), name, new Date());

        try {
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: Task %s: Doing a task during %d seconds\n", Thread.currentThread().getName(),
                    name, duration);
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s: Task %s: Finished on: %s\n",
                Thread.currentThread().getName(), name, new Date());

    }
}
