package com.wyl.dp.singleton;

/**
 * Created by wangyulin on 2/17/16.
 */
public class Singleton {
    private volatile static Singleton singleton ;
    private Singleton(){}
    public static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class) {
                if(singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
