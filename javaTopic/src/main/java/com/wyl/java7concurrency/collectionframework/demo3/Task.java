package com.wyl.java7concurrency.collectionframework.demo3;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class Task implements Runnable {

    private int id;

    private PriorityBlockingQueue<Event> queue;

    public Task(int id, PriorityBlockingQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Event event = new Event(id, i);
            queue.add(event);
        }
    }
}
