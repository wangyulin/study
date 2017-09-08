package com.wyl.java7concurrency.customizing.demo3;

/**
 * Created by wangyulin on 02/09/2017.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyThreadFactory myThreadFactory = new MyThreadFactory("MyThreadFactory");

        MyTask task = new MyTask();
        Thread thread = myThreadFactory.newThread(task);

        thread.start();
        thread.join();

        System.out.printf("Main: Thread information.\n");
        System.out.printf("%s\n", thread);
        System.out.printf("Main: End of the example.\n");
    }

}
