package com.wyl.thread.suspend;

/**
 * Created by wangyulin on 30/12/2016.
 */
public class GoodSuspend {

    public static Object u = new Object();

    public static class ChangeObjectThread extends Thread {
        volatile boolean suspendme = false;

        public void suspendMe() {
            suspendme = true;
        }

        public void resumeMe() {
            suspendme = false;
            synchronized (this) {
                notify ();
            }
        }

        public void run() {
            while(true) {
                synchronized (this) {
                    while(suspendme) {
                        try {
                            wait ();
                        } catch (InterruptedException e) {
                            e.printStackTrace ();
                        }
                    }

                    synchronized (u) {
                        System.out.println ("in ChangeObjectThread");
                        try {
                            Thread.sleep ( 1000 );
                        } catch (InterruptedException e) {
                            e.printStackTrace ();
                        }
                    }
                    Thread.yield ();
                }
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        public void run() {
            while (true) {
                synchronized (u) {
                    System.out.println ("in ReadObjectThread !");
                    try {
                        Thread.sleep ( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace ();
                    }
                }
                Thread.yield ();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread t1 = new ChangeObjectThread ();
        ReadObjectThread t2 = new ReadObjectThread ();

        t1.start ();
        t2.start ();

        Thread.sleep ( 5000 );

        t1.suspendMe();
        System.out.println ("Suspend T1 2 seconds !");
        Thread.sleep ( 2000 );
        System.out.println ("Resume T1");
        t1.resumeMe ();
    }

}
