package com.wyl.java7concurrency.executor.demo1;

/**
 * Created by wangyulin on 23/08/2017.
 */
public class Main {

    public static void main(String[] args) {
        Server server = new Server();

        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task " + i);
            server.executeTask(task);
        }

        server.endServer();
    }

}
