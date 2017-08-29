package com.wyl.java7concurrency.sync.demo0;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 23/08/2017.
 */
public class MyTest {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1); //此处可使用CountDownLatch(1)
        for (int i = 0; i < 3; i++) {
            new MyThread((char) (97 + i), phaser).start();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        phaser.arrive();  //此处可使用latch.countDown()
    }

}
