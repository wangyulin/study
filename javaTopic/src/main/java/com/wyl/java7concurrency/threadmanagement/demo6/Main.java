package com.wyl.java7concurrency.threadmanagement.demo6;

/**
 * Created by wangyulin on 20/08/2017.
 */
public class Main {

    public static void main(String[] args) {
        Task task = new Task();
        Thread t = new Thread(task);
        t.setUncaughtExceptionHandler(new ExceptionHandler());
        t.start();
    }

}
