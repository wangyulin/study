package com.wyl.thread.pool;

import java.util.concurrent.*;

/**
 * Created by wangyulin on 02/01/2017.
 */
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) {
        SynchronousQueue<Runnable> temp;
        ArrayBlockingQueue<Runnable> temp2;
        LinkedBlockingDeque<Runnable> temp3;
        PriorityBlockingQueue<Runnable> temp4;
        ScheduledExecutorService ese = Executors.newScheduledThreadPool (10);
        ese.scheduleAtFixedRate ( new Runnable () {
            @Override
            public void run() {
                try {
                    Thread.sleep ( 8000 );
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
                System.out.println (System.currentTimeMillis () / 1000);
            }
        }, 5, 3, TimeUnit.SECONDS );
    }

}
