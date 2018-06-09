package com.wyl.pattern;

/**
 * Created by wangyulin on 10/02/2018.
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("Real Subject Request");
    }

    @Override
    public void hello() {
        System.out.println("hello");
    }

    public void test() {
        System.out.println("test");
    }
}
