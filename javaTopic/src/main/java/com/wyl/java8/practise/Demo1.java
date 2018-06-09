package com.wyl.java8.practise;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Demo1 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 4, 9);//IntStream.rangeClosed(1, 3).mapToObj(Integer::new).collect(toList());
        List<List<Integer>> ans = subSets(list);
        System.out.println(ans);

        Map<String, String> map = new HashMap<>();
        map.computeIfAbsent("ABC", k -> k.substring(0, 1));
        map.computeIfAbsent("ABC", k -> k.substring(0, 1));
        map.computeIfAbsent("CBA", k -> k.substring(0, 1));
        System.out.println(map);
    }

    static List<List<Integer>> subSets(List<Integer> list) {
        System.out.println("Enter subSets ... " + list);
        //System.out.println("Enter subSets ... ");
        if(Objects.isNull(list) || list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }

        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());

        List<List<Integer>> subans = subSets(rest);
        List<List<Integer>> subans2 = insertAll(first, subans);
        return concat(subans,subans2);
    }

    static List<List<Integer>> concat(List<List<Integer>> a,
                                      List<List<Integer>> b) {
        List<List<Integer>> r = new ArrayList<>(a);
        r.addAll(b);
        return r;
    }

    static List<List<Integer>> insertAll(Integer first,
                                         List<List<Integer>> lists) {
        System.out.println("Enter insertAll()... " + "First :" + first + " lists : " + lists);
        List<List<Integer>> result = new ArrayList<>();
        for(List<Integer> list : lists) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }

        System.out.println("------------------------------------------------------------------ : " + result);
        return result;
    }

}
