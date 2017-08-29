package com.wyl.dp.singleton;

import java.lang.reflect.Constructor;

/**
 * Created by wangyulin on 2/17/16.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.hashCode());
        Singleton singleton1 = Singleton.getInstance();
        System.out.println(singleton1.hashCode());

        Class c = Class.forName(Singleton.class.getName());
        Constructor ct = c.getDeclaredConstructor();
        ct.setAccessible(true);
        Singleton singleton2 = (Singleton)ct.newInstance();
        System.out.println(singleton2.hashCode());

    }
}
