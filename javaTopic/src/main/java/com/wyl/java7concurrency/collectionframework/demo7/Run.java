package com.wyl.java7concurrency.collectionframework.demo7;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        Thread2 b = new Thread2();
        b.setName("t1");
        b.start();
        Thread.sleep(2000);
        System.out.println("main finished");
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String newString = new String("" + i);
            Math.random();
        }
    }

}

class Thread2 extends Thread {
    @Override
    public void run() {
        try {
            Thread1 a = new Thread1();
            a.start();
            System.out.println("begin join " + Thread.currentThread().getName());
            a.join();
            System.out.println("end join " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
