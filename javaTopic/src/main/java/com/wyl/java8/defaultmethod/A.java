package com.wyl.java8.defaultmethod;

public interface A {

    default void hello() {
        System.out.println("Hello from A");
    }

}
