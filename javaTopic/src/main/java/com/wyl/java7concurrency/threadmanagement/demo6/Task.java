package com.wyl.java7concurrency.threadmanagement.demo6;

/**
 * Created by wangyulin on 20/08/2017.
 */
public class Task implements Runnable {
    @Override
    public void run() {
        int a = Integer.parseInt("TTT");
    }
}
