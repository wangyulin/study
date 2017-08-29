package com.wyl.java7concurrency.sync.demo3;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 22/08/2017.
 */
public class Producer implements Runnable {

    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.set();
            try {
                Random random = new Random((new Date()).getTime());
                int value = (int) (random.nextDouble() * 10);
                TimeUnit.SECONDS.sleep(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
