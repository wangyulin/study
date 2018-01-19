package com.wyl.common.utils.base;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by wangyulin on 25/12/2017.
 */
public class ListMinus {

    public static void main(String[] args) {
        List<String> list_1 = new ArrayList<>();
        list_1.add("look");
        list_1.add("is");
        list_1.add("me");
        list_1.add("application");

        List<String> list_2 = new ArrayList<>();
        list_2.add("look");
        list_2.add("see");
        list_2.add("application");

        List<String> minux_list = list_1.stream().filter(item -> !list_2.contains(item)).collect(toList());
        minux_list.forEach(System.out::println);

    }

}
