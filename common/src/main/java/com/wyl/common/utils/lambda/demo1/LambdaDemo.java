package com.wyl.common.utils.lambda.demo1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LambdaDemo {
    public static void main(String[] args) {
        forEach();
    }

    private static void forEach() {

        LambdaDemo lambdaDemo = new LambdaDemo();

        List<String> list_2 =lambdaDemo.asList(LinkedList::new, "2ABC", "1xyz");
        Collections.sort(list_2, String::compareTo);
        list_2.stream().forEach(System.out::println);
    }

    public <T> List<T> asList(ICreater<List<T>> creater, T... t) {
        List<T> list = creater.create();
        for (T a : t)
            list.add(a);
        return list;
    }
}