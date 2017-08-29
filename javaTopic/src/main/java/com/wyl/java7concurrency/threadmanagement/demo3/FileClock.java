package com.wyl.java7concurrency.threadmanagement.demo3;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 20/08/2017.
 */
public class FileClock implements Runnable {

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.printf("%s\n",new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.printf("The FileClock has been interrupted");
            }
        }
    }
}
