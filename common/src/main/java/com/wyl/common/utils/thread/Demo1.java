package com.wyl.common.utils.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangyulin on 19/05/2017.
 */
public class Demo1 {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool (5);
        for(int i = 0 ; i < 1000000; i ++ ) {
            Task task = new Task("数据处理-" + i);
            es.submit(task);
        }

    }

    public static class Task implements Runnable {

        public String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Task " + this.name + " 开始执行 !!!" );
            try {
                Thread.sleep(Math.round(100000) % 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task " + this.name + " 执行结束 !!!" );
        }
    }

}
