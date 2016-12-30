package com.wyl.thread.volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangyulin on 30/12/2016.
 * 多线程操作发现，目前不能保证i自增不能保证原子性
 */
public class MutilThreadLong {
    public volatile static int i = 0;
    public static AtomicInteger one = new AtomicInteger ( 0 );

    public static class Plustask implements Runnable {
        public void run() {
            int k = 0;
            for(k = 0; k < 10000; k++) {
                i++;
                one.incrementAndGet ();
            }
            System.out.println (Thread.currentThread ().getName () + " k :" + k);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for(int i = 0; i< 10; i++) {
            threads[i] = new Thread(new Plustask ());
            threads[i].start ();
        }
        for(int i = 0; i< 10; i++) {
            threads[i].join ();
        }
        System.out.println (i);
        System.out.println ("one : " + one.get ());
    }

}
