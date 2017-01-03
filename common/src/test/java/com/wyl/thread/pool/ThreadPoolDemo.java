package com.wyl.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangyulin on 02/01/2017.
 */
public class ThreadPoolDemo {

    public static class MyTask implements Runnable {
        public void run() {
            System.out.println (System.currentTimeMillis () + " : Thread ID : " + Thread.currentThread ().getId ());
            ThreadLocal<String> local = new ThreadLocal<String> ();
            local.set ( Thread.currentThread ().getName () );
            local.set ( Thread.currentThread ().getName () + "-RE" );
            System.out.println (local.get ());
            try {
                Thread.sleep ( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask ();
        ExecutorService es = Executors.newFixedThreadPool (5);
        //ExecutorService es = Executors.newCachedThreadPool ();
        for(int i = 0; i < 10; i++ ) {
            es.submit ( task );
        }
        es.shutdown ();
    }

}