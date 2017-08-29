package com.wyl.dp.singleton;

/**
 * Created by wangyulin on 2/17/16.
 */
public class Singleton1 {
    private static class SingletonHolder {
        private static final Singleton1 INSTANCE = new Singleton1();
    }
    private Singleton1 (){}
    public static final Singleton1 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
