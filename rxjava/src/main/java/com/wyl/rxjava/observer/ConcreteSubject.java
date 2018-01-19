package com.wyl.rxjava.observer;

/**
 * Created by wangyulin on 22/06/2017.
 */
class ConcreteSubject extends Subject {
    public void doSomething(){
        System.out.println("被观察者事件反生");
        this.notifyObserver();
    }
}
