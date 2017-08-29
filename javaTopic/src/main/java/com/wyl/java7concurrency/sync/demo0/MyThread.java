package com.wyl.java7concurrency.sync.demo0;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 23/08/2017.
 */
public class MyThread extends Thread {

    private char c;

    private Phaser phaser;

    public MyThread(char c, Phaser phaser) {
        this.c = c;
        this.phaser = phaser;
    }

    public void run() {
        phaser.awaitAdvance(phaser.getPhase()); //此处可使用latch.await()
        System.out.printf("Thread %c is starting ...\n", c);
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread %c is finished.\n", c);
    }
}
