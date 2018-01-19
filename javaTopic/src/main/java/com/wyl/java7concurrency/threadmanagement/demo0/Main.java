package com.wyl.java7concurrency.threadmanagement.demo0;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class Main {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                while(true) {
                    System.out.println("Beging ... " + counter);
                    System.out.println("End. " + counter);
                    counter ++;
                    if (Thread.currentThread().isInterrupted()) {
                        //!Thread.currentThread().isInterrupted()
                        System.out.printf("Thread is interrupted .\n");
                        break;
                    }
                }
            }
        });

        thread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
