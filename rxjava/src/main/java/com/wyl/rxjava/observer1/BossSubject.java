package com.wyl.rxjava.observer1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyulin on 22/06/2017.
 */
public class BossSubject implements Subject {


    private List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("成功告诉观察者我需要被通知");
        }
    }


    @Override
    public void removeObserver(Observer observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            System.out.println("成功告诉观察者我不需要被通知了");
        }
    }


    @Override
    public void notifyAllObserver() {
        System.out.println("开始通知");
        for (Observer obs : observers) {
            obs.notifyPerson();
        }
        System.out.println("所有的都已通知！");
    }


}
