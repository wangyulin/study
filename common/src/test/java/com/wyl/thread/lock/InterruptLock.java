package com.wyl.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangyulin on 30/12/2016.
 */
public class InterruptLock implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock (  );
    public static ReentrantLock lock2 = new ReentrantLock (  );
    int lock;

    public InterruptLock(int lock) {
        this.lock = lock;
    }

    public void run () {
        try {
            if(lock == 1) {
                lock1.lockInterruptibly ();
                System.out.println ();
                try {
                    Thread.sleep ( 500 );
                } catch (InterruptedException e){}
                lock2.lockInterruptibly ();
            } else {
                lock2.lockInterruptibly ();
                try {
                    Thread.sleep ( 500 );
                } catch (InterruptedException e){}
                lock1.lockInterruptibly ();
            }
        } catch (InterruptedException e) {
            e.printStackTrace ();
        } finally {
            if(lock1.isHeldByCurrentThread ()) {
                lock1.unlock ();
            }
            if(lock2.isHeldByCurrentThread ()) {
                lock2.unlock ();
            }
            System.out.printf ( "%d:线程退出%n", Thread.currentThread ().getId () );
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptLock r1 = new InterruptLock ( 1 );
        InterruptLock r2 = new InterruptLock ( 2 );

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start ();
        t2.start ();
        Thread.sleep ( 1000 );
        t2.interrupt ();
    }

}
