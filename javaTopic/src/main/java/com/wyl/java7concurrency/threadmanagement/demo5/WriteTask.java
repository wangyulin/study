package com.wyl.java7concurrency.threadmanagement.demo5;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 20/08/2017.
 */
public class WriteTask implements Runnable {

    private Deque<Event> deque;

    public WriteTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(String.format("The thread %s has generated an event", Thread.currentThread().getId()));
            System.out.printf("Add Event(%d) : %d\n", deque.size(), event.getDate().getTime());
            deque.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
