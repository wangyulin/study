package com.wyl.rxjava.observer3;

/**
 * Created by wangyulin on 22/06/2017.
 */
public class Client {

    public static void main(String[] args) {
        BossSubject bossSubject = new BossSubject();

        Worker w1 = new Worker();
        bossSubject.registerObserver(w1);
        Worker w2 = new Worker();
        bossSubject.registerObserver(w2);

        bossSubject.setChanged();
        bossSubject.notifyAllObserver();

    }

}
