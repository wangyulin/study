package com.wyl.java8.testlambda;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by wangyulin on 17/01/2018.
 */
public class TestDemo {

    private static final int LENGTH = 100000;

    public static void main(String[] args) {

        System.out.println((char)'A'); //65
        System.out.println((char)'a'); //97
        String str = "AdaSfdd";
        int sum = str.chars().filter(x -> (x >= 'a' && x <= 'z')).reduce(0, (acc, x) -> acc + 1);
        System.out.println(sum);

        List<String> list = new ArrayList<>();
        Random r = new Random();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < LENGTH; i++) {
            list.add("wangyulin" + r.nextInt(100));
        }

        //System.out.println(list);

        long start_1 = System.nanoTime();
        List<String> toUpperCase = new ArrayList<>();
        for (String item : list) {
            toUpperCase.add(item.toUpperCase());
        }
        long end_1 = System.nanoTime();

        System.out.println("Time_1 : " + (end_1 - start_1));

        long start_2 = System.nanoTime();
        //long max_2 = list.parallelStream().parallel().reduce(Integer::max).get();
        List<String> toUpperCase_2 = list.parallelStream().map(x -> x.toUpperCase()).collect(toList());
        long end_2 = System.nanoTime();
        System.out.println("Time_2 : " + (end_2 - start_2));
    }

}
