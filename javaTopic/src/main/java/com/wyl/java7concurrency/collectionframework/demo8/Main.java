package com.wyl.java7concurrency.collectionframework.demo8;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by wangyulin on 02/09/2017.
 */
public class Main {

    public static void main(String[] args) {
        final int THREADS = 100;
        AtomicIntegerArray vector = new AtomicIntegerArray(1000);

        Incrementer incrementer = new Incrementer(vector);
        Decrementer decrementer = new Decrementer(vector);

        Thread threadInvrementer[] = new Thread[THREADS];
        Thread threadDecrementer[] = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            threadInvrementer[i] = new Thread(incrementer);
            threadDecrementer[i] = new Thread(decrementer);

            threadInvrementer[i].start();
            threadDecrementer[i].start();
        }

        for (int i = 0; i < THREADS; i++) {
            try {
                threadInvrementer[i].join();
                threadDecrementer[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < vector.length(); i++) {
            //if(vector.get(i) != 0) {
                System.out.printf("Vector[%d] : %d\n", i, vector.get(i));
            //}
        }

        System.out.printf("Main: End of the example\n");
    }

}
