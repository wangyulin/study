package com.wyl.thread.pool;

import com.wyl.jdk8.Demo1;
import com.wyl.thread.stop.StopThreadUnSafe;
import lombok.Data;

import java.util.Random;

/**
 * Created by wangyulin on 02/01/2017.
 */
public class ThreadPoolDemo {

    public static class MyTask implements Runnable {
        public void run() {
            System.out.println (System.currentTimeMillis () + " : Thread ID : " + Thread.currentThread ().getId ());
            ThreadLocal<String> local = new ThreadLocal<>();
            local.set ( Thread.currentThread ().getName () );
            local.set ( Thread.currentThread ().getName () + "-RE" );
            System.out.println (local.get ());
            try {
                Thread.sleep ( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
    }

    public static ThreadLocal<String> threadLocal;

    private static ThreadLocal<U> logs = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(() -> {
            threadLocal = new ThreadLocal<>();
            threadLocal.set("OK_3");
        }).start();

        new Thread(() -> {
            System.out.println(threadLocal.get());

            ThreadLocal<String> tl_1 = new ThreadLocal<>();
            tl_1.set("Tl_1");

            ThreadLocal<String> tl_2 = new ThreadLocal<>();
            tl_2.set("Tl_2");

            ThreadLocal<String> tl_3 = new ThreadLocal<>();

            tl_2.set("T2_1");

            System.out.println(tl_1.get());
            System.out.println("t3.get() = " + tl_3);
        }).start();

        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        /*for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                U u = logs.get();
                if (u == null) {
                    u = new U();
                    logs.set(u);
                }
                System.out.println(Thread.currentThread().getName() + "..." + u);
            }).start();
        }*/

        /*
        MyTask task = new MyTask ();
        ExecutorService es = Executors.newFixedThreadPool (5);
        //ExecutorService es = Executors.newCachedThreadPool ();
        for(int i = 0; i < 1; i++ ) {
            es.submit ( task );
        }
        es.shutdown ();*/
        /*
        Task t = new Task();
        new Thread(t).start();
        new Thread(t).start();
        */

    }

    @Data
    static class U {
        private String name;
        private int age;
    }

    static class Task implements Runnable {

        ThreadLocal<String> threadLocal;

        @Override
        public void run() {
            threadLocal = new ThreadLocal<>();
            threadLocal.set("OK_" + new Random().nextInt(10));
            System.out.println(threadLocal.get());
        }
    }

}
