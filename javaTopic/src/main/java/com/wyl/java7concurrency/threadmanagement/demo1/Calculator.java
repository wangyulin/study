package com.wyl.java7concurrency.threadmanagement.demo1;

/**
 * Created by wangyulin on 19/08/2017.
 */
public class Calculator implements Runnable {

    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: %d * %d = %d \n",
                    Thread.currentThread().getName(), number, i, i*number);
        }
    }
}
