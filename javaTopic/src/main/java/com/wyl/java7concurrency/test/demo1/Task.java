package com.wyl.java7concurrency.test.demo1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class Task implements Runnable {

    private Lock lock;

    public Task(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            lock.lock();
            System.out.printf("%s: Get the lock.\n", Thread.currentThread().getName());

            try {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.printf("%s: Free the Lock.\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
