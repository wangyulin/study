package com.wyl.basic.cyclicBarrier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Demo {

    public static int count = 1;

    private synchronized void incr(long sleepTime) {
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(count++ + ", ");
    }

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        Demo demo1 = new Demo();
        Demo demo2 = new Demo();

        ExecutorService es = Executors.newFixedThreadPool(20);

        List<Task> callables = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            Task task = new Task(demo1, cyclicBarrier, 1);
            callables.add(task);
        }

        for(int i = 0; i < 10; i++) {
            Task task = new Task(demo2, cyclicBarrier, 1);
            callables.add(task);
        }

        es.invokeAll(callables);
        es.shutdown();

        //m(cyclicBarrier, demo1);
        //m(cyclicBarrier, demo2);
    }

    static class Task implements Callable<String> {

        private Demo demo;
        private CyclicBarrier cyclicBarrier;
        private long sleepTime;

        public Task(Demo demo, CyclicBarrier cyclicBarrier, long sleepTime){
            this.demo = demo;
            this.cyclicBarrier = cyclicBarrier;
            this.sleepTime = sleepTime;
        }

        @Override
        public String call() throws Exception {
            demo.incr(sleepTime);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /*private static void m(CyclicBarrier cyclicBarrier, Demo demo1) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                demo1.incr();
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }*/
}
