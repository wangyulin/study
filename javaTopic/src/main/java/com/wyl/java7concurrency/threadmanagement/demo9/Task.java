package com.wyl.java7concurrency.threadmanagement.demo9;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 21/08/2017.
 */
public class Task implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
