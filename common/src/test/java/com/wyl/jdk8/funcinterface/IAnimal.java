package com.wyl.jdk8.funcinterface;

/**
 * Created by wangyulin on 07/01/2017.
 */
public interface IAnimal {

    default void breath() {
        System.out.println ("breath");
    }

}
