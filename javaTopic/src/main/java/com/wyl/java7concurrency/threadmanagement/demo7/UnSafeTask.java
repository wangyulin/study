package com.wyl.java7concurrency.threadmanagement.demo7;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 21/08/2017.
 */
public class UnSafeTask implements Runnable {

    private Date startDate;
    @Override
    public void run() {
        startDate = new Date();
        System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate);
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate);
    }
}
