package com.wyl.sync;

/**
 * Created by wangyulin on 4/5/16.
 */
public class Demo4 {

    public static void main(String[] args) {
        final Bank4 bank= new Bank4();
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

class Bank4 {
    private static ThreadLocal<Integer> count = new ThreadLocal<Integer>(){

        @Override
        protected Integer initialValue() {
            // TODO Auto-generated method stub
            return 0;
        }

    };

    // 存钱
    public void addMoney(int money) {
        count.set(count.get()+money);
        System.out.println(System.currentTimeMillis() + "存进：" + money);

    }

    // 取钱
    public void subMoney(int money) {
        if (count.get() - money < 0) {
            System.out.println("余额不足");
            return;
        }
        count.set(count.get()- money);
        System.out.println(+System.currentTimeMillis() + "取出：" + money);
    }

    // 查询
    public void lookMoney() {
        System.out.println("账户余额：" + count.get());
    }
}
