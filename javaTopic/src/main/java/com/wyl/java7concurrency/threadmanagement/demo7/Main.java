package com.wyl.java7concurrency.threadmanagement.demo7;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 21/08/2017.
 */
public class Main {

    public static void main(String[] args) {
        SafeTask safeTask = new SafeTask();
        //UnSafeTask unSafeTask = new UnSafeTask();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(safeTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
