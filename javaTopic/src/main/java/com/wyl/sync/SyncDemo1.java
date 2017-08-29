package com.wyl.sync;

/**
 * Created by wangyulin on 4/14/16.
 */
public class SyncDemo1 {

    public static void main(String[] args) {
        SyncDemo1 sy = new SyncDemo1();
        for (int i = 0; i < 3; i++) {
            Thread thread = sy.new MyThread();
            thread.start();
        }
    }

    class MyThread extends Thread {

        public void run() {
            Sync sync = new Sync();
            sync.test();
        }
    }

    public class Sync {

        public synchronized void test() {
            System.out.println("test开始..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test结束..");
        }

    }

}
