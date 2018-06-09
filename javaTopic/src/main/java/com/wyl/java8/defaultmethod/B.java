package com.wyl.java8.defaultmethod;

public interface B extends A {

    default void hello() {
        System.out.println("Hello from B");
    }

}
