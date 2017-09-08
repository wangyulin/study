package com.wyl.java7concurrency.collectionframework.demo7;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class Main {

    public static void main(String[] args) {
        Thread threads[] = new Thread[3];

        ThreadLocalRandom.current();

        Math.random();
        Random r = new Random();
        System.out.println(r.nextInt(10) + " .");

        for (int i = 0; i < 3; i++) {
            TaskLocalRandom task = new TaskLocalRandom();
            threads[i] = new Thread(task);
            threads[i].start();
        }
    }

}
