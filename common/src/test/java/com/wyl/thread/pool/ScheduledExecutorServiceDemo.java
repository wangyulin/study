package com.wyl.thread.pool;

import java.util.Hashtable;
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
        ConcurrentLinkedQueue<String> temp5;
        CopyOnWriteArrayList<String> temp6;
        ArrayBlockingQueue<String> temp7;
        ConcurrentSkipListMap<String, String> temp8;
        ConcurrentHashMap<String, String> temp9;
        Hashtable<String, String> temp10;
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
