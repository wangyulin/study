package com.wyl.java8.demo2;

/**
 * Created by wangyulin on 17/01/2018.
 */
//@FunctionalInterface
public interface FunctionalInterfaceTest {

    void test1();

    default void test2() {

    }

    default void test3() {

    }

    @Override
    String toString();

}


