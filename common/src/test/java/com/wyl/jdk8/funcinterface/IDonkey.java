package com.wyl.jdk8.funcinterface;

/**
 * Created by wangyulin on 07/01/2017.
 */
public interface IDonkey {
    void eat();
    default void run() {
        System.out.println ("Donkey run");
    }
}
