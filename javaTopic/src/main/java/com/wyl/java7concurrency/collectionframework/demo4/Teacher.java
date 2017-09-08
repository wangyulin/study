package com.wyl.java7concurrency.collectionframework.demo4;

import java.util.concurrent.DelayQueue;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class Teacher implements Runnable {

    private DelayQueue<Student> students;

    public Teacher(DelayQueue<Student> students){
        this.students = students;
    }

    @Override
    public void run() {
        try {
            System.out.println("Test start ...");
            while(!Thread.interrupted()){
                students.take().run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
