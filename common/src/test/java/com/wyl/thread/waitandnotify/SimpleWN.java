package com.wyl.thread.waitandnotify;

/**
 * Created by wangyulin on 29/12/2016.
 */
public class SimpleWN {

    final static Object object = new Object();

    public static class T1 extends Thread{
        public void run() {
            synchronized (object) {
                System.out.println (System.currentTimeMillis () + " : T1 start !");
                try {
                    System.out.println (System.currentTimeMillis () + " : T1 wait for object !");
                    object.wait ();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
                System.out.println (System.currentTimeMillis () + " : T1 end !");
            }
        }
    }

    public static class T2 extends Thread{
        public void run() {
            synchronized (object) {
                System.out.println (System.currentTimeMillis () + " : T2 start ! notify one thread");
                object.notify ();
                try {
                    Thread.sleep ( 2000 );
                    System.out.println (System.currentTimeMillis () + " : T2 end !");
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }

            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start ();
        t2.start ();
    }

}
