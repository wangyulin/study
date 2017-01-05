package com.wyl.parallel.productandconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangyulin on 04/01/2017.
 */
public class Producer implements Runnable {
    private volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue;
    private static AtomicInteger count = new AtomicInteger (  );
    private static final int SLEEPTIME = 1000;

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        PCData data = null;
        Random r = new Random();

        System.out.println ("start producer id = " + Thread.currentThread ().getId ());
        try {
            while (isRunning) {
                Thread.sleep ( SLEEPTIME );
                data = new PCData ( count.getAndIncrement () );
                System.out.println (data + " is put into queue");
                if(!queue.offer ( data, 2, TimeUnit.SECONDS )) {
                    System.err.println ( "failed to put data: " + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace ();
            Thread.currentThread ().interrupt ();
        }
    }

    public void stop() {
        isRunning = false;
        System.out.println (Thread.currentThread ().getId () + " It's stopping");
    }
}
