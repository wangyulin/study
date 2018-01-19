package com.wyl.rxjava.observer3;


import java.util.Observable;
import java.util.Observer;

/**
 * Created by wangyulin on 22/06/2017.
 */
public class BossSubject extends Observable {

    public void registerObserver(Observer observer) {
        addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        removeObserver(observer);
    }

    public void setChanged() {
        super.setChanged();
    }

    public void notifyAllObserver() {
        notifyObservers();
    }
}
