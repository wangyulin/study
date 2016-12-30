package com.wyl.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangyulin on 30/12/2016.
 */
public class FairLock  implements Runnable {

    public static ReentrantLock fairLock = new ReentrantLock ( true );

    public void run () {
        while(true) {
            try {
                fairLock.lock ();
                System.out.println (Thread.currentThread ().getName () + "获得锁");
            } finally {
                fairLock.unlock ();
            }
        }
    }

    public static void main(String[] args) {
        FairLock r1 = new FairLock ();
        Thread t1 = new Thread ( r1, "Thread_T1" );
        Thread t2 = new Thread ( r1, "Thread_T2" );
        t1.start ();
        t2.start ();
    }

}
