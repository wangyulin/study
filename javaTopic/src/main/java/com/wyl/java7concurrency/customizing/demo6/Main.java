package com.wyl.java7concurrency.customizing.demo6;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by wangyulin on 07/09/2017.
 */
public class Main {

    public static void main(String[] args) {
        int array[] = new int[10000];

        ForkJoinPool pool = new ForkJoinPool();

        Task task = new Task("Task", array, 0, array.length);

        pool.invoke(task);

        pool.shutdown();
        System.out.printf("Main: End of the program.\n");
    }

}
