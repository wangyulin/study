package com.wyl.basic;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseDataTypeDemo {

    public static final int SIZE = 100000;

    public static void main(String[] args) throws InterruptedException {

        Integer a = 100;
        Integer b = 100;

        System.out.println(a == b);

        a = 128;
        b = 128;
        a.intValue();

        new String("123").toCharArray();

        System.out.println(a == b);

        /*Random random = new Random();
        int [] int_a = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            int_a[i] = random.nextInt(100000);
        }

        TimeUnit.SECONDS.sleep(10000);

        for (int i = 0; i < SIZE; i+= 1000) {
            System.out.println(int_a[i]);
        }*/
    }

}
