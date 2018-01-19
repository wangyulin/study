package com.wyl.rxjava.observer;

/**
 * Created by wangyulin on 22/06/2017.
 */
class ConcreteObserver1 implements Observer {
    public void update() {
        System.out.println("观察者1收到信息，并进行处理。");
    }
}
