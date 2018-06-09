package com.wyl.pattern;

/**
 * Created by wangyulin on 10/02/2018.
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = new Proxy(new RealSubject());
        subject.request();
    }
}
