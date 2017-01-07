package com.wyl.jdk8.funcinterface;

import java.util.Comparator;
import java.util.List;

/**
 * Created by wangyulin on 07/01/2017.
 */
public class Mule implements IHouse, IDonkey, IAnimal{

    public static void main(String[] args) {
        List l;
        Comparator c;
        Mule m = new Mule();
        m.run ();
        m.breath ();
    }

    @Override
    public void eat() {
        System.out.println ("Mule eat");
    }

    @Override
    public void run() {
        IHouse.super.run ();
    }
}
