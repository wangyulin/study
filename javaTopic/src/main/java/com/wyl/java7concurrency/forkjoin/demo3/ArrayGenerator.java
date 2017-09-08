package com.wyl.java7concurrency.forkjoin.demo3;

import java.util.Random;

/**
 * Created by wangyulin on 30/08/2017.
 */
public class ArrayGenerator {

    public int[] generateArray(int size) {
        int array[] = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10);
        }
        return array;
    }

}
