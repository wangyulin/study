package com.wyl.dp.singleton;

/**
 * Created by wangyulin on 2/17/16.
 */
public class Demo1 {
    public static void main(String[] args) throws Exception {
        Singleton1 singleton1 = Singleton1.getInstance();
        System.out.println(singleton1.hashCode());

    }
}
