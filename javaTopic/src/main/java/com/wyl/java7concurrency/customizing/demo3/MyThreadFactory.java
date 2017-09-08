package com.wyl.java7concurrency.customizing.demo3;

import java.util.concurrent.ThreadFactory;

/**
 * Created by wangyulin on 02/09/2017.
 */
public class MyThreadFactory implements ThreadFactory {

    private int counter;

    private String prefix;

    public MyThreadFactory(String prefix) {
        this.prefix = prefix;
        counter = 1;
    }

    @Override
    public Thread newThread(Runnable r) {
        MyThread myThread = new MyThread(r, prefix + "-" + counter);
        counter++;
        return myThread;
    }
}
