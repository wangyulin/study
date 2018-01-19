package com.wyl.rxjava.observer3;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by wangyulin on 22/06/2017.
 */
public class Worker implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Throwable t;
        Error e;
        Exception exception;
        System.out.println("被通知了...");
    }

}
