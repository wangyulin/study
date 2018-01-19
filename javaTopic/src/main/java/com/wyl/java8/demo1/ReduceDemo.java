package com.wyl.java8.demo1;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * Created by wangyulin on 17/01/2018.
 */
public class ReduceDemo {

    public static void main(String[] args) {
        int count = Stream.of(1, 2, 3)
                .reduce(10, (acc, element) -> acc + element);
        System.out.println(count);

        Optional<Integer> sum = Stream.of(1, 3, 5, 7, 9)
                .reduce((acc, item) -> acc + item);
        System.out.println(sum.get());

        System.out.println("//////////////////////////////////////////////////////////");

        ArrayList<Integer> accResult_ = Stream.of(1, 2, 3, 4)//.parallel()
                .reduce(new ArrayList<Integer>(),
                        new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, Integer item) {
                                acc.add(item);
                                System.out.println("TAG_A item: " + item);
                                System.out.println("TAG_A acc+ : " + acc);
                                return acc;
                            }
                        },
                        new BinaryOperator<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> apply(ArrayList<Integer> acc, ArrayList<Integer> item) {
                                acc.addAll(item);
                                System.out.println("TAG_B item: " + item);
                                System.out.println("TAG_B acc+ : " + acc);
                                return acc;
                            }
                        });
        System.out.println("accResult_: " + accResult_);

        System.out.println("//////////////////////////////////////////////////////////");

        BinaryOperator<Integer> accumulator = (acc, element) -> acc + element;
        int sum_1 = accumulator.apply(
                accumulator.apply(
                        accumulator.apply(0,
                                1),
                        2),
                3);
        System.out.println(sum_1);


    }

}
