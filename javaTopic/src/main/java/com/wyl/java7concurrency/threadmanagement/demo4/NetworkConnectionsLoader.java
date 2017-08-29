package com.wyl.java7concurrency.threadmanagement.demo4;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 20/08/2017.
 */
public class NetworkConnectionsLoader implements Runnable {
    @Override
    public void run() {
        System.out.printf("Beginning network connections loading : %s\n", new Date());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Network connections loading hasfinished: %s\n", new Date());
    }
}
