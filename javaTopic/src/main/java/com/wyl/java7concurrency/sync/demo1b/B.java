package com.wyl.java7concurrency.sync.demo1b;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class B {

    public void m() {
        synchronized (getClass()) {
            System.out.printf("%s: Beging run m ...\n", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s: End run m ...\n", Thread.currentThread().getName());
        }
    }

    public void n() {
        synchronized (this) {
            System.out.printf("%s: Beging run n ...\n", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s: End run n ...\n", Thread.currentThread().getName());
        }
    }

}
