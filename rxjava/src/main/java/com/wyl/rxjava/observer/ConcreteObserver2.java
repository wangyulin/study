package com.wyl.rxjava.observer;

/**
 * Created by wangyulin on 22/06/2017.
 */
class ConcreteObserver2 implements Observer {
    public void update() {
        System.out.println("观察者2收到信息，并进行处理。");
    }
}
