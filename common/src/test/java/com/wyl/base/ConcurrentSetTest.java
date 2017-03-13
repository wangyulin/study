package com.wyl.base;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangyulin on 13/03/2017.
 */
public class ConcurrentSetTest {

    public static void main(String[] args) {
        Set<String> names = Collections.newSetFromMap(
                new ConcurrentHashMap<String, Boolean>()
        );
        names.add("Brian Goetz");
        names.add("Victor Grazi");
        names.add("Heinz Kabutz");
        names.add("Brian Goetz");
        System.out.println("names = " + names);
        names.clear();
        System.out.println("names = " + names);
    }

}
