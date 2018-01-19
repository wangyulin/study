package com.wyl.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangyulin on 15/06/2017.
 */

public class StaticSynchronized {

    public static void main(String[] args) {
        Job1 job1 = new Job1();
        Job2 job2 = new Job2();

        A a = new A();
        job1.setA(a);
        job2.setA(a);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(job1);
        executorService.execute(job2);

        executorService.shutdown();

        System.out.println("......");
    }

}

class Job1 implements Runnable {

    private A a;

    public void setA(A a) {
        this.a = a;
    }

    @Override
    public void run() {

        A.m();
    }
}

class Job2 implements Runnable {

    private A a;

    public void setA(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        A.n();
    }
}

class A {

    public static synchronized void m() {
        System.out.println("a.m() 执行开始");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a.m() 执行结束");
    }

    public static synchronized void n() {
        System.out.println("a.n() 执行开始");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a.n() 执行结束");
    }

}
