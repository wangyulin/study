package com.wyl.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by wangyulin on 31/12/2016.
 */
public class SemapDemo implements Runnable {
    final Semaphore semp = new Semaphore(5);
    public void run() {
        try {
            semp.acquire ();
            Thread.sleep ( 5000 );
            System.out.println ( Thread.currentThread ().getName () + " : Done !");
            semp.release ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool (20);
        final SemapDemo demo = new SemapDemo ();
        for( int i = 0; i < 20;i ++ ) {
            exec.submit ( demo );
        }
    }
}
