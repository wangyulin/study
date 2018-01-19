package com.wyl.rxjava.observer1;

/**
 * Created by wangyulin on 22/06/2017.
 */
public interface Subject {
    //需要通知的对象先告诉观察者
    void registerObserver(Observer observer);

    //观察者移除不需要通知的对象
    void removeObserver(Observer observer);

    //通知所需需要被通知的对象
    void notifyAllObserver();
}
