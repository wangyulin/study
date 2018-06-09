package com.wyl.dp.observer.demo3;

public interface Observerable {

    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();

}