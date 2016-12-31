package com.wyl.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wangyulin on 31/12/2016.
 */
public class ReadWriteLockDemo {

    private static Lock lock = new ReentrantLock (  );
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock (  );
    private static Lock readLock = reentrantReadWriteLock.readLock ();
    private static Lock writeLock = reentrantReadWriteLock.writeLock ();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock ();
            System.out.println (Thread.currentThread ().getName () + " : GET read lock");
            System.out.println (Thread.currentThread ().getName () + " : read value :" + value);
            Thread.sleep ( 2000 );
            return value;
        } finally {
            System.out.println (Thread.currentThread ().getName () + " : release read lock");
            lock.unlock ();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock ();
            System.out.println (Thread.currentThread ().getName () + " : GET write lock");
            //Thread.sleep ( 1000 );
            System.out.println (Thread.currentThread ().getName () + " : write value :" + index);
            value = index;
        } finally {
            System.out.println (Thread.currentThread ().getName () + " : release read lock");
            lock.unlock ();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo ();

        Runnable readRunnale = new Runnable () {
            @Override
            public void run() {
                try {
                    int res1 = (int) demo.handleRead ( readLock );
                    System.out.println (Thread.currentThread ().getName () + " Result :" + res1);
                    //int res2 = (int) demo.handleRead ( lock );
                    //System.out.println ("Result :" + res2);
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
        };

        Runnable writeRunnale = new Runnable () {
            @Override
            public void run() {
                try {
                    //demo.handleWrite ( writeLock, new Random ().nextInt () );
                    demo.handleWrite ( lock, new Random ().nextInt () );
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
        };

        for(int i = 0; i < 18; i++ ) {
            new Thread ( readRunnale ).start ();
            //Thread.sleep ( 100 );
        }

        for(int i = 18; i < 20; i++ ) {
            new Thread ( writeRunnale ).start ();
            //Thread.sleep ( 100 );
        }

    }

}
