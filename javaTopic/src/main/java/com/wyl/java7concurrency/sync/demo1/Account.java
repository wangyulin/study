package com.wyl.java7concurrency.sync.demo1;

import lombok.Data;

/**
 * Created by wangyulin on 22/08/2017.
 */
@Data
public class Account {

    private double balance;

    public synchronized void addAmount(double amount) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tmp += amount;
        balance = tmp;
    }

    public synchronized void subtractAmount(double amount) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tmp -= amount;
        balance = tmp;
    }

}
