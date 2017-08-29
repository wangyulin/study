package com.wyl.java7concurrency.threadmanagement.demo3;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 20/08/2017.
 */
public class FileMain {

    public static void main(String[] args) {
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);

        thread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }

}
