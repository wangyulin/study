package com.wyl.cglib.lazyLoader.demo2;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class Main {

    public static void main(String[] args) {
        TB tb = new TB();
        System.out.println(tb.getCid());
        System.out.println("--");
        System.out.println(tb.getBean().getUserName());
    }

}
