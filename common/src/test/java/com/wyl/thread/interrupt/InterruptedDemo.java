package com.wyl.thread.interrupt;

/**
 * Created by wangyulin on 29/12/2016.
 */
public class InterruptedDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            public void run() {
                while(true) {
                    System.out.println ("It's running ... !");
                    if(Thread.currentThread ().isInterrupted ()) {
                        System.out.println ("Interrupted !");
                        break;
                    }
                    try {
                        Thread.sleep ( 2000 );
                    } catch (InterruptedException e) {
                        System.out.println ("Interrupted When Sleep !");
                        Thread.currentThread ().interrupt ();
                    }
                    Thread.yield();
                }
            }
        };
        t1.start ();
        Thread.sleep ( 1000 );
        t1.interrupt ();
        System.out.println ("线程t1是否中断" + Thread.currentThread ().isInterrupted ());
    }

}
