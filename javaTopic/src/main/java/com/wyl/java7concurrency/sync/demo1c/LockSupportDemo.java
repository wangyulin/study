package com.wyl.java7concurrency.sync.demo1c;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by wangyulin on 04/03/2018.
 */
public class LockSupportDemo {

    public static void main(String[] args) throws InterruptedException {
        String a = new String("游戏");

        Thread thread = new Thread(() -> {
            System.out.println("周末我要打游戏");
            LockSupport.park();
            System.out.println("-----");
            System.out.println("陪女朋友逛街");
        });

        thread.start();
        Thread.sleep(3000);
        System.out.println("女朋友准备要喊男朋友逛街");
        System.out.println("*****");
        LockSupport.unpark(thread);
    }

}
