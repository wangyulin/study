package com.wyl.java7concurrency.threadmanagement.demo5;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 20/08/2017.
 */
public class CleanerTask extends Thread {

    private Deque<Event> deque;

    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            Date date = new Date();
            /*try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            clean(date);
        }
    }

    private void clean(Date date) {
        long difference;
        boolean delete;
        //System.out.printf("deque.size() : %d\n", deque.size());
        if(deque.size() == 0) {
            return;
        }

        delete = false;

        do{
            Event e = deque.getLast();
            difference = date.getTime() - e.getDate().getTime();
            if(difference > 10000) {
                System.out.printf("Cleaner: %s\n", e.getEvent());
                deque.removeLast();
                delete = true;
            }
        } while(difference > 10000);

        if(delete) {
            System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
        }
    }
}
