package com.wyl.java7concurrency.customizing.demo4;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 03/09/2017.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(2);
        Task task = new Task();
        System.out.printf("Main: %s\n", new Date());

        executor.schedule(task, 1, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(3);

        task = new Task();
        System.out.printf("Main: %s\n", new Date());

        executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(10);

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.printf("Main: End of the program.");
    }

}
