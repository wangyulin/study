package com.wyl.java7concurrency.customizing.demo1;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 02/09/2017.
 */
public class SleepTwoSecondsTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return new Date().toString();
    }
}
