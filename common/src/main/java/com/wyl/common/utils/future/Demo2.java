package com.wyl.common.utils.future;

import java.util.concurrent.*;

/**
 * Created by wangyulin on 21/04/2017.
 */
public class Demo2 {

    public static void main(String[] args) {
        Task task = new Task();
        //第一种方式
        /*ExecutorService executor = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executor.submit(futureTask);
        executor.shutdown();*/

        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        /*Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();*/

        //第三种方式
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        forkJoinPool.submit(futureTask);

        try {
            Thread.sleep(1);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }

}
