package com.wyl.java7concurrency.collectionframework.demo8;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by wangyulin on 02/09/2017.
 */
public class Incrementer implements Runnable {

    private AtomicIntegerArray vector;

    public Incrementer(AtomicIntegerArray vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        for (int i = 0; i < vector.length(); i++) {
            vector.getAndIncrement(i);
        }
    }
}
