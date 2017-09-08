package com.wyl.java7concurrency.collectionframework.demo4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class Student implements Runnable, Delayed {

    private String name;
    private long workTime;
    private long submitTime;
    private boolean isForce = false;
    private CountDownLatch countDownLatch;

    public Student(String name, long workTime, CountDownLatch countDownLatch){
        this.name = name;
        this.workTime = workTime;
        this.submitTime = TimeUnit.NANOSECONDS.convert(workTime, TimeUnit.NANOSECONDS)+System.nanoTime();
        this.countDownLatch = countDownLatch;
        System.out.printf("Student %s workTime %d\n", this.name, this.workTime);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(submitTime - System.nanoTime(),  TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(o == null || ! (o instanceof Student)) return 1;
        if(o == this) return 0;
        Student s = (Student)o;
        if (this.workTime > s.workTime) {
            return 1;
        }else if (this.workTime == s.workTime) {
            return 0;
        }else {
            return -1;
        }
    }

    @Override
    public void run() {
        if (isForce) {
            System.out.println(name + " 交卷, 希望用时" + workTime + "分钟"+" ,实际用时 120分钟" );
        }else {
            System.out.println(name + " 交卷, 希望用时" + workTime + "分钟"+" ,实际用时 " + workTime + " 分钟");
        }
        countDownLatch.countDown();
    }

    public boolean isForce() {
        return isForce;
    }

    public void setForce(boolean isForce) {
        this.isForce = isForce;
    }
}
