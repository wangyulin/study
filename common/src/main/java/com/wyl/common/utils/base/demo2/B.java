package com.wyl.common.utils.base.demo2;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class B extends A {

    @Override
    public void method1() {
        System.out.println("B.method1 is starting ...");
        super.method1();
        System.out.println("B.method1 is ending ...");
    }
}
