package com.wyl.java7concurrency.threadmanagement.demo4;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 20/08/2017.
 */
public class DataSourcesLoader implements Runnable {

    @Override
    public void run() {
        System.out.printf("Beginning data sources loading : %s\n", new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Date sources loading hasfinished: %s\n", new Date());
    }
}
