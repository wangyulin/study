package com.wyl.java7concurrency.customizing.demo3;


import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 02/09/2017.
 */
public class MyTask implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
