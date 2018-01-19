package com.wyl.java8.demo1;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.primitives.Ints.asList;
import static java.lang.Character.isDigit;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

/**
 * Created by wangyulin on 17/01/2018.
 */
public class StreamDemo {

    public static void main(String[] args) {
        Predicate<Integer> atleast5 = x -> x > 5;
        System.out.println(atleast5.test(5));
        BinaryOperator<Integer> f = (x, y) ->  x + y;
        System.out.println(f.apply(3, 5));

        Stream<String> stream = Stream.of("a", "c");
        stream.map(str -> str.toUpperCase()).forEach(System.out::println);

        List<String> stringList = Stream.of("A", "1V", "V1")
                .filter(str -> isDigit(str.charAt(0)))
                .collect(toList());
        stringList.forEach(System.out::println);

        //TODO Stream reuse
        //List<String> list_A = stream.collect(toList());

        String[] array = {"a", "b", "c", "d", "e"};
        Supplier<Stream<String>> streamSupplier = () -> Stream.of(array);
        streamSupplier.get();

        List<String> list = Arrays.asList("a","b");

        List<Integer> merge = Stream.of(asList(1, 3), asList(3,6))
                .flatMap(numbers -> numbers.stream())
                .collect(toList());
        merge.forEach(System.out::println);

        list.stream().parallel().filter(x -> {
            System.out.println(x);
            return x.equals("a");
        });

        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 8, 0, 1, 12)
                .stream()
                .parallel()
                .collect(groupingBy(x -> x % 10))
                .forEach((x, y) -> System.out.println(x + ":" + y));

        System.out.println("OK");

        List<String> items =
                Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

        Map<String, Long> result =
                items.stream().collect(
                        groupingBy(p -> p, counting())
                );

        System.out.println(result);

        Map<String, Long> finalMap = new LinkedHashMap<>();

        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        System.out.println(finalMap);

        System.out.println("//////////////////////////////////////////////////////////");
        List<String> helloList =
                Arrays.asList("hello welcome",
                              "world hello",
                              "hello world",
                              "hello world welcome");

        Stream<Stream<String>> x = helloList.stream()
                .map(item -> Arrays.stream(item.split(" ")));
                x.distinct()
                .collect(toList())
                .forEach(s -> s.forEach(it -> System.out.println(it)));

        System.out.println("//////////////////////////////////////////////////////////");

        helloList.stream()
                .flatMap(item -> Arrays.stream(item.split(" ")))
                .distinct()
                .collect(toList())
                .forEach(s -> System.out.println(s));

        System.out.println("//////////////////////////////////////////////////////////");

        List<Stream<String>> listResult = helloList
                .stream()
                .map(item -> Arrays.stream(item.split(" ")))
                .distinct()
                .collect(toList());

        List<String> listResult2 = helloList
                .stream()
                .flatMap(item -> Arrays.stream(item.split(" ")))
                .distinct()
                .collect(toList());

        helloList.stream()
                .map(item -> item.split(" "))
                .flatMap(a -> Arrays.stream(a))
                .distinct()
                .collect(toList())
                .forEach(s -> System.out.println(s));

        System.out.println("//////////////////////////////////////////////////////////");

        List<String> list2 = Arrays.asList("hello", "hi", "你好");
        List<String> list3 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        list2.stream()
                .map(item -> list3.stream().map(item2 -> item + " " + item2)).distinct().collect(Collectors.toList())
                .forEach(a -> {
                    System.out.println("-------------");
                    a.forEach(System.out::println);
                    System.out.println("-------------");
                });

        list2.stream()
                .flatMap(item -> list3.stream().map(item2 -> item + " " + item2))
                .distinct().collect(Collectors.toList()).forEach(System.out::println);

        List<Stream<String>> list2Result = list2.stream()
                .map(item -> list3.stream().map(item2 -> item + " " + item2))
                .collect(Collectors.toList());

        List<String> list2Result2 = list2.stream()
                .flatMap(item -> list3.stream().map(item2 -> item + " " + item2))
                .collect(Collectors.toList());
    }
}
