package com.wyl.java7concurrency.collectionframework.demo5;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class NANODemo {

    public static void main(String[] args) {
        System.out.println("=======================");

        try {
            TimeUnit.NANOSECONDS.sleep(5000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=======================");
    }

}
