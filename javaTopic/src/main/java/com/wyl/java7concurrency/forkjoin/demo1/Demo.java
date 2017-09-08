package com.wyl.java7concurrency.forkjoin.demo1;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 29/08/2017.
 */
public class Demo {

    public static void main(String[] args) {
        //System.out.println(Runtime.getRuntime().availableProcessors());
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(100);

        Task task = new Task(products, 0, products.size(), 0.20);

        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        //pool.invoke(task);

        do{
            System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());

            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }while(!task.isDone());

        pool.shutdown();

        if(task.isCompletedAbnormally()) {
            System.out.printf("Main: The process has completed normally.\n");
        }

        System.out.println("==========================================");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            //if(product.getPrice() != 10) {
                //System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
            //}
        }

        System.out.printf("Main: End of the program.\n");
    }

}
