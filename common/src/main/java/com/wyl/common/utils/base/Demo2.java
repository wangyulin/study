package com.wyl.common.utils.base;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wangyulin on 27/12/2017.
 */
public class Demo2 {

    public static void main(String[] args) {
        int i = 2;
        int j = 3;
        System.out.println(i);

        List<String> list1 = Lists.newArrayList();
        List<String> list2 = Lists.newArrayList();
        list1.add("1");
        list1.add("2");
        list1.add("3");

        list2 = list1.stream().map(string -> { return "stream().map()处理之后：" + string;}).collect(Collectors.toList());

        list2.stream().forEach(string -> {
            System.out.println(string);
        });

        List<Long> longs = new ArrayList<>();
        longs.add(Long.valueOf(1));
        longs.add(Long.valueOf(2));
        longs.add(Long.valueOf(3));
        longs.add(Long.valueOf(10));

        Long a = Long.valueOf(10);
        Long b = Long.valueOf(11);
        System.out.println(longs.contains(b));
    }

}
