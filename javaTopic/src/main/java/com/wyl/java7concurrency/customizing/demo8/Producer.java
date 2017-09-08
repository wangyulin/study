package com.wyl.java7concurrency.customizing.demo8;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class Producer implements Runnable {

    private MyPriorityTransferQueue<Event> buffer;

    public Producer(MyPriorityTransferQueue<Event> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event(Thread.currentThread().getName(), i);
            buffer.put(event);
        }
    }
}
