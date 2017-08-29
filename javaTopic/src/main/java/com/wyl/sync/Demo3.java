package com.wyl.sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangyulin on 4/5/16.
 */
public class Demo3 {

    public static void main(String[] args) {
        final Bank bank= new Demo3().new Bank();
        Thread tadd=new Thread(new Runnable() {
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bank.addMoney(100);
                    bank.lookMoney();
                    System.out.println("\n");

                }
            }
        });

        Thread tsub = new Thread(new Runnable() {
            public void run() {
                while(true){
                    bank.subMoney(100);
                    bank.lookMoney();
                    System.out.println("\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        tsub.start();

        tadd.start();
    }

    class Bank {
        private int count = 0;
        private ReentrantLock lock = new ReentrantLock();
        public synchronized void addMoney(int money) {
            lock.lock();
            try {
                count += money;
                System.out.println(System.currentTimeMillis() + "存进 : " + money);
            } finally {
                lock.unlock();
            }
        }

        public void subMoney(int money) {
            lock.lock();
            try {
                if (count - money < 0) {
                    System.out.println("余额不足");
                    return;
                }
                count -= money;
                System.out.println(+System.currentTimeMillis() + "取出：" + money);
            } finally {
                lock.unlock();
            }
        }

        //查询
        public void lookMoney(){
            System.out.println("账户余额："+count);
        }
    }


}


