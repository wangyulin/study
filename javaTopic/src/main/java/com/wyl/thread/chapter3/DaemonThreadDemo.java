package com.wyl.thread.chapter3;

import static java.lang.Thread.sleep;

/**
 * Created by wangyulin on 28/07/2017.
 */
public class DaemonThreadDemo {

    public static void main(String[] args) {
        Thread t = new Thread(new Calculator(10));
        t.setDaemon(false);
        t.start();
    }

}

class Calculator implements Runnable {

    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(number);
            System.out.println(Thread.currentThread().getState());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}