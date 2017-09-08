package com.wyl.java7concurrency.collectionframework.demo4;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        int studentNumber = 20;
        CountDownLatch countDownLatch = new CountDownLatch(studentNumber+1);
        DelayQueue<Student> students = new DelayQueue<>();
        Random random = new Random();

        for (int i = 0; i < studentNumber; i++) {
            students.put(new Student("Student_" + ( i + 1 ), 30 + random.nextInt(120), countDownLatch));
        }

        Thread teacherThread =new Thread(new Teacher(students));
        students.put(new EndExam(students, 120,countDownLatch,teacherThread));
        teacherThread.start();
        countDownLatch.await();
        System.out.println("考试时间到，全部交卷！");
    }

}
