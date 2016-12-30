package com.wyl.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangyulin on 30/12/2016.
 * 该程序的问题在于虽然偶尔有机会卡城两线程成功结束，但是有花费很长时间。
 * 两个线程很笨，要来来回回退让很多次。才能解开死锁的问题。
 */
public class TryLock implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock (  );
    public static ReentrantLock lock2 = new ReentrantLock (  );
    int lock;

    public TryLock (int lock) {
        this.lock = lock;
    }

    public void run () {
        //System.out.println (Thread.currentThread ().getName () + " is Running ...");
        if(lock == 1) {
            while (true) {
                if(lock1.tryLock ()) {
                    //System.out.println (Thread.currentThread ().getName () + " lock1");
                    try {
                        try {
                            Thread.sleep ( 500 );
                        } catch (InterruptedException e) {
                        }
                        if(lock2.tryLock ()) {
                            //System.out.println (Thread.currentThread ().getName () + " lock2");
                            try {
                                System.out.println (Thread.currentThread ().getName () + " : My Job done");
                                return;
                            } finally {
                                //System.out.println (Thread.currentThread ().getName () + " unlock2");
                                lock2.unlock ();
                            }
                        }
                    } finally {
                        //System.out.println (Thread.currentThread ().getName () + " unlock1");
                        lock1.unlock ();
                        /*try {
                            Thread.sleep ( 300 );
                        } catch (InterruptedException e) {
                        }*/
                    }
                }
            }
        } else {
            while(true) {
                if(lock2.tryLock ()) {
                    //System.out.println (Thread.currentThread ().getName () + " lock2");
                    try{
                        try {
                            Thread.sleep ( 500 );
                        } catch (InterruptedException e) {
                        }
                        if(lock1.tryLock ()) {
                            //System.out.println (Thread.currentThread ().getName () + " lock1");
                            try {
                                System.out.println (Thread.currentThread ().getName () + " : My Job done");
                                return;
                            } finally {
                                //System.out.println (Thread.currentThread ().getName () + " unlock1");
                                lock1.unlock ();
                            }
                        }
                    } finally {
                        //System.out.println (Thread.currentThread ().getName () + " unlock2");
                        lock2.unlock ();
                        /*try {
                            Thread.sleep ( 300 );
                        } catch (InterruptedException e) {
                        }*/
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TryLock r1 = new TryLock ( 1 );
        TryLock r2 = new TryLock ( 2 );
        Thread t1 = new Thread ( r1 , "T1");
        Thread t2 = new Thread ( r2 , "T2");
        t1.start ();
        t2.start ();
    }

}
