package com.wyl.java7concurrency.test.demo2;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(3);

        for (int i = 0; i < 3; i++) {
            Task task = new Task(i + 1, phaser);
            Thread thread = new Thread(task);
            thread.start();
        }

        for (int i = 0; i < 10; i++) {
            System.out.printf("*****************************************\n");
            System.out.printf("Main: Phaser Log\n");
            System.out.printf("Main: Phaser: Phase: %d\n", phaser.getPhase());
            System.out.printf("Main: Phaser: Registered parties: %d\n", phaser.getRegisteredParties());
            System.out.printf("Main: Phaser: Arrived parties: %d\n", phaser.getArrivedParties());
            System.out.printf("Main: Phaser: Unarrived Parties: %d\n", phaser.getUnarrivedParties());
            System.out.printf("*****************************************\n");

            TimeUnit.SECONDS.sleep(1);
        }
    }

}
