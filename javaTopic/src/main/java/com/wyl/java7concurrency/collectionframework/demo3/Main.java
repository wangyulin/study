package com.wyl.java7concurrency.collectionframework.demo3;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class Main {

    public static void main(String[] args) {
        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();

        Thread[] taskThreads = new Thread[2];

        for (int i = 0; i < taskThreads.length; i++) {
            Task task = new Task(i, queue);
            taskThreads[i] = new Thread(task);
        }

        for (int i = 0; i < taskThreads.length; i++) {
            taskThreads[i].start();
        }

        for (int i = 0; i < taskThreads.length; i++) {
            try {
                taskThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: Queue Size: %d\n", queue.size());

        for (int i = 0; i < taskThreads.length * 1000; i++) {
            Event event = queue.poll();
            System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());
        }

        System.out.printf("Main: Queue Size: %d\n", queue.size());
        System.out.printf("Main: End of the program\n");
    }

}
