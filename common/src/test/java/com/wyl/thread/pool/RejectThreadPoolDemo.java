package com.wyl.thread.pool;

import sun.rmi.runtime.RuntimeUtil;

import java.util.concurrent.*;

/**
 * Created by wangyulin on 02/01/2017.
 */
public class RejectThreadPoolDemo {

    public static class MyTask implements Runnable {
        public void run() {
            System.out.println (System.currentTimeMillis () + " : Thread ID : " + Thread.currentThread ().getId ());
            try {
                Thread.sleep ( 100 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        ExecutorService es = new ThreadPoolExecutor ( 5, 5,
                0L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable> ( 10 ),
                Executors.defaultThreadFactory (),
                new RejectedExecutionHandler () {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println (r.toString () + " is discard");
                    }
                } );

        for (int i = 0 ; i < Integer.MAX_VALUE; i++ ) {
            es.submit ( task );
            Thread.sleep ( 10 );
        }
    }

}
