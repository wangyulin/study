package com.wyl.sync;

/**
 * Created by wangyulin on 4/14/16.
 */
public class SyncDemo2 {

    public static void main(String[] args) {
        SyncDemo2 sn = new SyncDemo2();
        A a = new A();
        new Thread1(a).start();
        A b = new A();
        new Thread2(b).start();
    }
}

class Thread1 extends Thread {
    private A a;
    public Thread1(A a) {
        super("Thread1");
        this.a = a;
    }
    public void run() {
        a.x();
    }
}

class Thread2 extends Thread {
    private A a;
    public Thread2(A a) {
        super("Thread1");
        this.a = a;
    }
    public void run() {
        a.y();
    }
}

class A{

    public static synchronized void x() {
        System.out.println("进入x方法");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("退出x方法");
    }

    public static synchronized void y() {
        System.out.println("进入y方法");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("退出y方法");
    }
}
