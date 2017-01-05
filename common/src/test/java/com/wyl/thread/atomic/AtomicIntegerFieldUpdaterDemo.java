package com.wyl.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by wangyulin on 04/01/2017.
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static class Candiate{
        int id;
        volatile int score;
    }

    public final static AtomicIntegerFieldUpdater<Candiate> scoreUpdate = AtomicIntegerFieldUpdater.newUpdater ( Candiate.class, "score" );
    public static AtomicInteger allScore = new AtomicInteger ( 0 );

    public static void main(String[] args) throws InterruptedException {
        final Candiate stu = new Candiate ();
        Thread[] t = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            t[i] = new Thread() {
                @Override
                public void run() {
                    if(Math.random () > 0.4) {
                        scoreUpdate.incrementAndGet ( stu );
                        allScore.incrementAndGet ();
                    }
                }
            };
            t[i].start ();
        }
        for (int i = 0; i < 10000; i++) {
            t[i].join ();
        }
        System.out.println ("score = " + stu.score);
        System.out.println ("allScore = " + allScore);
    }

}
