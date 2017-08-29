package com.wyl.java7concurrency.executor.demo4;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by wangyulin on 29/08/2017.
 */
public class Main {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
        System.out.printf("Main: Starting at : %s\n", new Date());

        Task task = new Task("Task");

        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        for (int i = 0; i < 1; i++) {
            System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();

        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        System.out.printf("Main: Finished at: %s\n", new Date());
    }

}
