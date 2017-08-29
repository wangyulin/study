package com.wyl.java7concurrency.threadmanagement.demo5;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by wangyulin on 20/08/2017.
 */
public class Main {

    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<>();

        WriteTask writer = new WriteTask(deque);

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writer);
            thread.start();
        }

        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();
    }

}
