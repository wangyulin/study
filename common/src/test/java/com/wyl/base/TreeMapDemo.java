package com.wyl.base;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by wangyulin on 15/06/2017.
 */
public class TreeMapDemo {

    public static void main(String[] args) {
        ForkJoinPool a;
        ExecutorService es = Executors.newFixedThreadPool(100);
        Map<String, String> map = new LinkedHashMap<>();//new TreeMap<>();
        map.put("xyz", "123");
        map.put("abc", "111");
        ThreadLocal y;

        for(String key : map.keySet()) {
            System.out.println(map.get(key));
        }
    }

}
