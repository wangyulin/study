package com.wyl.common.utils.thread.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangyulin on 24/02/2018.
 */
public class Demo {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Hello World");
        Thread thread = new Thread(runnable);
        thread.start();

        String ss = "Hello";
        String[] aa = ss.split("");
        String[] bb = {"H", "e", "l", "l", "o"};
        String[] strings = {"Hello", "World"};

        List<Stream<String>> streamList = Stream.of(strings)
                .map(str -> str.split(""))
                .map(str -> Stream.of(str))
                .collect(Collectors.toList());

        List<String> stringList = Stream.of(strings)
                .map(str -> str.split(""))
                .flatMap(str -> Stream.of(str))
                .collect(Collectors.toList());

        Stream.of(strings)
                .map(str -> str.split(""))
                .flatMap(str -> Stream.of(str))
                .forEach(System.out::println);

        Supplier<Stream<String>> testStream = () -> Stream.of(strings);
        testStream.get().forEach(System.out::println);
        testStream.get().forEach(System.out::println);

    }

}
