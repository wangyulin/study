package com.wyl.java7concurrency.sync.demo4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangyulin on 22/08/2017.
 */
public class PrintQueue {

    private final Lock queueLock = new ReentrantLock(true);

    public void printjob(Object document) {
        queueLock.lock();
        try {
            Long duration = (long)(Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() +
                    ": PrintQueue : Printing a Job during " + (duration / 1000) + " seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }


}
