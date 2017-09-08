package com.wyl.java7concurrency.forkjoin.demo2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 30/08/2017.
 */
public class Main {

    public static void main(String[] args) {
        DocumentMock mock = new DocumentMock();
        String [][] document = mock.generateDocument(100, 1000, "the");

        DocumentTask task = new DocumentTask(document, 0, 100, "the");

        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);

        do{
            System.out.printf("********************************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: task Counter: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Counter: %d\n", pool.getStealCount());
            System.out.printf("********************************************************\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(!task.isDone());

        pool.shutdown();

        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.printf("Main: The word appears %d in the document", task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
