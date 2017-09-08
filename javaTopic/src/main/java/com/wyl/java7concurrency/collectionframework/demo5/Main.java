package com.wyl.java7concurrency.collectionframework.demo5;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        int cacheNumber = 10;
        int liveTime = 0;
        Cache<String, Integer> cache = new Cache<String, Integer>();

        for (int i = 0; i < cacheNumber; i++) {
            liveTime = random.nextInt(10);
            System.out.println(i + "  " + liveTime);
            cache.put(i + "", i, 9000000000L); //random.nextInt(liveTime <= 0 ? 1 : liveTime)
            if (random.nextInt(cacheNumber) > 7) {
                liveTime = random.nextInt(10);
                System.out.println(i+"  "+liveTime);
                cache.put(i + "", i, 4000000000L); //random.nextInt(liveTime <= 0 ? 1 : liveTime)
            }
        }

        TimeUnit.SECONDS.sleep(20000);
        System.out.println();
    }

}
