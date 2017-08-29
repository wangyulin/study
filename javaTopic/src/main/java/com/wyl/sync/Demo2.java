package com.wyl.sync;

/**
 * Created by wangyulin on 4/5/16.
 */
public class Demo2 {

    public static void main(String[] args) {
        final Bank bank=new Bank();
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
}

class Bank {
    private int count = 0;

    public synchronized void addMoney(int money) {
        synchronized (this) {
            count += money;
            System.out.println(System.currentTimeMillis() + "存进 : " + money);
        }
    }

    public void subMoney(int money) {
        synchronized (this) {
            if (count - money < 0) {
                System.out.println("余额不足");
                return;
            }
            count -= money;
            System.out.println(+System.currentTimeMillis() + "取出：" + money);
        }
    }

    //查询
    public void lookMoney(){
        System.out.println("账户余额："+count);
    }
}
