package com.wyl.common.utils.future;

import java.util.concurrent.*;

/**
 * Created by wangyulin on 21/04/2017.
 */
public class Demo1 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();

        result.cancel(false);
        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果 : " + result.get(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }

}
