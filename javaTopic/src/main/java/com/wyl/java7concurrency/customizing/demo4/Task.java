package com.wyl.java7concurrency.customizing.demo4;


import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 03/09/2017.
 */
public class Task implements Runnable {

    @Override
    public void run() {
        System.out.printf("Task : Begin.\n");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Task: End.\n");
    }
}
