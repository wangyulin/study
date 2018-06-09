package com.wyl.pattern;

/**
 * Created by wangyulin on 10/02/2018.
 */
public class Proxy implements Subject {

    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        System.out.println("Before");
        try {
            realSubject.request();
        } catch(Exception e) {
            System.out.println("ex : " + e.getMessage());
            throw e;
        } finally {
            System.out.println("After");
        }
    }

    @Override
    public void hello() {
        realSubject.hello();
    }
}
