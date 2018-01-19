package com.wyl.java7concurrency.customizing.demo5;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 06/09/2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String aaa = "TEST";
        aaa.intern();
        Object obj;
        MyWorkThreadFactory factory = new MyWorkThreadFactory();
        ForkJoinPool pool = new ForkJoinPool(4, factory, null, false);
        int array[] = new int[100000];

        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }

        MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length);

        pool.execute(task);

        task.join();
        pool.shutdown();

        pool.awaitTermination(1, TimeUnit.DAYS);

        System.out.printf("Main: Result: %d\n", task.get());

        System.out.printf("Main: End of the program\n");
    }

}
