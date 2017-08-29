package com.wyl.java7concurrency.executor.demo4;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by wangyulin on 29/08/2017.
 */
public class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s: Starting at : %s\n", name, new Date());
        //return "Hello world";
    }
}
