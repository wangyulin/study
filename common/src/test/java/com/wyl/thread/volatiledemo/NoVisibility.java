package com.wyl.thread.volatiledemo;

/**
 * Created by wangyulin on 30/12/2016.
 */
public class NoVisibility {
    private volatile static boolean ready;
    private volatile static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while(!ready);
            System.out.println (number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread ().start ();
        Thread.sleep ( 1000 );
        number = 42;
        ready = true;
        Thread.sleep ( 1000 );
    }

}
