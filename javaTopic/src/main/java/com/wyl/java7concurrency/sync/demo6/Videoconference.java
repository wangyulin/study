package com.wyl.java7concurrency.sync.demo6;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wangyulin on 23/08/2017.
 */
public class Videoconference implements Runnable {

    private final CountDownLatch controller;

    public Videoconference(int number) {
        controller = new CountDownLatch(number);
    }

    public void arrive(String name) {
        System.out.printf("%s has arrived.\n", name);
        controller.countDown();

        System.out.printf("Videoconference: Waiting for %d participants.\n", controller.getCount());
    }

    @Override
    public void run() {
        System.out.printf("Videoconference: Initialization: %d participants.\n", controller.getCount());
        try {
            controller.await();
            System.out.printf("Videoconference: All the participants have come\n");
            System.out.printf("Videoconference: Let's start ...\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
