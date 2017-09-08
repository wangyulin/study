package com.wyl.java7concurrency.customizing.demo8;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyPriorityTransferQueue<Event> buffer = new MyPriorityTransferQueue<>();

        Producer producer = new Producer(buffer);

        Thread productThreads[] = new Thread[10];
        for (int i = 0; i < productThreads.length; i++) {
            productThreads[i] = new Thread(producer);
            productThreads[i].start();
        }

        Consumer consumer = new Consumer(buffer);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());

        Event myEvent = new Event("Core Event", 0);
        buffer.put(myEvent);
        System.out.printf("Main: MyEvent has been transfered.\n");


        for (int i = 0; i < productThreads.length; i++) {
            try {
                productThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        TimeUnit.SECONDS.sleep(1);

        System.out.printf("Main: Buffer: Consumer count: %d\n", buffer.getWaitingConsumerCount());

        myEvent = new Event("Core Event 2",0);
        buffer.put(myEvent);

        consumerThread.join();

        System.out.printf("Main: End of the program\n");
    }

}
