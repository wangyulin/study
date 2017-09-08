package com.wyl.java7concurrency.collectionframework.demo7;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class TaskLocalRandom implements Runnable {

    public TaskLocalRandom() {
        ThreadLocalRandom t = ThreadLocalRandom.current();
        System.out.println(t);
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: %d\n", name, ThreadLocalRandom.current().nextInt(10));
        }
    }
}
