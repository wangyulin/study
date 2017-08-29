package com.wyl.basic;

import java.io.IOException;

/**
 *
 * Created by wangyulin on 4/13/16.
 */
public class JavaOverride {

    public static void main(String[] args) throws Exception {
        Dog dog = new Dog();
        boolean flag = dog instanceof Object;
        System.out.println(flag);
        dog.move();
    }

}

class Animal{

    public void move() throws Exception {
        System.out.println("动物可以移动 !");
    }

}

class Dog extends Animal {

    @Override
    public void move() throws Exception {
        super.move();
        System.out.println("狗可以移动 !");
        //throw new IOException();
    }

    public int move(int a) {
        System.out.println(a);
        return a + 1;
    }

}
