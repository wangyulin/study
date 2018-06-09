package com.wyl.java7concurrency.sync.demo1b;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class Main {

    public static void main(String[] args) {
        //syncMethod();
        syncClass();
    }

    public static void syncClass() {
        B b = new B();

        Thread t1 = new Thread(() -> b.m());

        Thread t2 = new Thread(() -> b.n());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main: End of the program.");
    }

    public static void syncMethod() {
        A a = new A();
        //A.m();
        Thread t1 = new Thread(() -> {
            A.m();
            a.n();
        });

        Thread t2 = new Thread(() -> {
            //A.m();
            a.n();
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main: End of the program.");
    }

}
