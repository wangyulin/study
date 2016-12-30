package com.wyl.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangyulin on 30/12/2016.
 */
public class ReenterLockCondition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock (  );
    public static Condition condition = lock.newCondition ();

    public void run () {
        System.out.println (Thread.currentThread ().getName () + " is running ...");
        try{
            lock.lock ();
            System.out.println (Thread.currentThread ().getName () + " get lock");
            System.out.println (Thread.currentThread ().getName () + " await");
            condition.await ();
            System.out.println (Thread.currentThread ().getName () + " is going on");
        } catch (InterruptedException e) {
            e.printStackTrace ();
        } finally {
            lock.unlock ();
            System.out.println (Thread.currentThread ().getName () + " unlock");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition tl = new ReenterLockCondition ();
        Thread t1 = new Thread(tl, "Thread-T1");
        t1.start ();
        Thread.sleep ( 1000 );
        lock.lock ();
        System.out.println (Thread.currentThread ().getName () + " get lock");
        condition.signal ();
        lock.unlock ();
        System.out.println (Thread.currentThread ().getName () + " unlock");
    }

}
