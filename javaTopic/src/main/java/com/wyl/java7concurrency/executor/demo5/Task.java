package com.wyl.java7concurrency.executor.demo5;

import java.util.concurrent.Callable;

/**
 * Created by wangyulin on 29/08/2017.
 */
public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        while(true) {
            System.out.printf("Task: Test\n");
            Thread.sleep(100);
        }
        //return null;
    }
}
