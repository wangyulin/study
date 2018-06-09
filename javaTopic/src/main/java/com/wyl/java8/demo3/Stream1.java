package com.wyl.java8.demo3;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.summarizingInt;

public class Stream1 {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList(
                "bcd", "cde", "def", "abc");
        List<String> result = list.stream()
                .filter(e -> e.length() >= 3)
                .map(e -> e.charAt(0))
                .map(e -> String.valueOf(e))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);
        File[] hiddenFiles = new File(".").listFiles(file -> file.isHidden());
        new File(".").listFiles(File::isHidden);
        List<Apple> inventory = new ArrayList<>();

        filterApples(inventory, Apple::isGreenApple);
        filterApples(inventory, Apple::isHeavyApple);
        filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        filterApples(inventory, (Apple a) -> a.getWeight() > 150);

        Apple a1 = new Apple("Red", 151);
        Apple a2 = new Apple("Green", 149);

        List<Apple> appleList = Arrays.asList(a1, a2);
        prettyPrintApple(appleList, new AppleFancyFormatter());
        //prettyPrintApple(appleList, a -> "An apple of " + a.getWeight() + "g");
        System.out.println("---");
        appleList.sort(comparing(Apple::getColor));

        appleList.stream().mapToInt(Apple::getWeight).sum();

        prettyPrintApple(appleList, new AppleFancyFormatter());

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> h2 = f.compose(g);
        int res = h.apply(1);
        System.out.println(res);
        System.out.println(h2.apply(1));

        System.out.println("---");
        List<String> applesColor = appleList.stream()
                .map(Apple::getColor).collect(Collectors.toList());
        System.out.println(applesColor);

        String[] arrayWords = {"Goodbye", "World"};
        List<String> words = Arrays.asList(arrayWords);
        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters);

        if(appleList.stream().anyMatch(a -> a.getColor().equals("Red"))) {
            System.out.println("有红色苹果");
        }

        if (appleList.stream().allMatch(a -> a.getWeight() > 100)) {
            System.out.println("每一个苹果重量都超过100g");
        }

        if(appleList.stream().noneMatch(a -> a.getWeight() > 160)) {
            System.out.println("没有苹果重量超过160g");
        }

        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        Optional<Integer> res1 = numbers.stream().reduce(Integer::sum);
        System.out.println(res1.get());
        Optional<Integer> res2 = numbers.stream().reduce(Integer::max);
        System.out.println(res2.get());

        Stream<int[]> py = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(1, 100)
                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0 && b >= a)
                        .mapToObj(b ->
                            new int[]{a, b, (int)Math.sqrt(a*a + b*b)}
                        )
                );

        Stream<double[]> py1 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                    IntStream.rangeClosed(a, 100)
                        .mapToObj(
                                b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                        .filter(t -> t[2] % 1 == 0)
                );

        //py1.map(o -> new int[]{(int) o[0], (int) o[1], (int) o[2]}).forEach(t -> System.out.println(t[0] + "-" + t[1] + "-" + t[2]));

        long uniqueWords = 0;
        try(Stream<String> lines =
                    Files.lines(Paths.get("/Users/wangyulin/workdir/miui/study/javaTopic/src/main/resources/word.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap( line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(uniqueWords);

        IntSummaryStatistics intSummaryStatistics = Stream.of(1,2,3,4,5,6,7,8,9,10)
                .collect(summarizingInt(Integer::intValue));
        System.out.println(intSummaryStatistics);

        System.out.println(appleList.stream().map(Apple::getColor).collect(joining(",")));



        /*Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0]+t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);*/

        /*Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));*/

        /*
        List<Integer> n1 = Arrays.asList(1, 2, 3);
        List<Integer> n2 = Arrays.asList(3, 4);
        List<int[]> pairs = n1.stream()
                .flatMap(
                            i -> n2.stream()
                                    .filter(j -> (i + j) % 3 == 0)
                                    .map(j -> new int[] {i, j})
                )
                .collect(Collectors.toList());
        System.out.println(pairs);
        */

        /*
        List<String> famous = new ArrayList<String>();
        famous.add("liudehua");
        famous.add("madehua");
        famous.add("liushishi");
        famous.add("tangwei");
        for (int i = 0; i < famous.size(); i++ ) {
            String s = famous.get(i);
            if(s.equals("madehua")){
                famous.remove(i);
            }
        }
        */

        /*
        for(Iterator<String> it = famous.iterator(); it.hasNext();){
            String s = it.next();
            if(s.equals("madehua")){
                it.remove();
            }
        }
        */

        /*
        for(Iterator<String> it = famous.iterator(); it.hasNext();){
            System.out.println(it.next());
        }
        */

        /*
        System.out.println("Sequential sum done in: " +
            measureSumPref(ParallelStreams::sequentialSum, 1000000) + " msecs");

        System.out.println("Iterative sum done in: " +
                measureSumPref(ParallelStreams::iterativeSum, 1000000) + " msecs");

        System.out.println("Parallel sum done in: " +
                measureSumPref(ParallelStreams::parallelSum, 1000000) + " msecs");

        System.out.println("Parallel sum done in: " +
                measureSumPref(ParallelStreams::parallelRangedSum, 1000000) + " msecs");
        */

        /*
        final String SENTENCS = " Nel mezzo del cammin di nostra vita " +
                "mi ritrovai in una selva oscura" +
                " che la dritta via era  smarrita";
        System.out.println(countWordsiteratively(SENTENCS));
        */

        /*
        Function<String, String> headerProcessing =
                (String text) -> "From Raoul, Mario and Alan: " + text;

        Function<String, String> spellCheckerProcessing =
                (String text) -> text.replaceAll("labda", "lambda");

        Function<String, String> pipeline =
                headerProcessing.andThen(spellCheckerProcessing);

        String res_2 = pipeline.apply("Aren't labdas really sexy!!");
        System.out.println(res_2);
        */

        System.out.println("---");
        List<Integer> numberList = Arrays.asList(2, 3, 4, 5);
        numberList.stream()
                .peek(x -> System.out.println("From stream: " + x))
                .map(x -> x  + 17)
                .peek(x -> System.out.println("After map: " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("After filter: " + x))
                .limit(3)
                .peek(x -> System.out.println("After limit: " + x))
                //.forEach(System.out::println)
                .collect(Collectors.toList());
    }

    public static int countWordsiteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for(char c : s.toCharArray()) {
            if(Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if(lastSpace) counter ++;
                lastSpace = false;
            }
        }
        return counter;
    }

    public static long measureSumPref(Function<Long, Long> addr, long n) {
        long fastest = Long.MAX_VALUE;
        for(int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = addr.apply(n);
            long duration = (System.nanoTime() - start) / 1000000;
            System.out.println("Result: " + sum);
            if(duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter) {
        for(Apple apple : inventory) {
            String output = appleFormatter.accept(apple);
            System.out.println(output);
        }
    }

    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public interface AppleFormatter {
        String accept(Apple a);
    }

    public static class AppleFancyFormatter implements AppleFormatter {
        @Override
        public String accept(Apple a) {
            String characteristic = a.getWeight() > 150 ? "heavy" : "light";
            return "A " + characteristic + " " + a.getColor() + " apple";
        }
    }

    public static class AppleSimpleFormatter implements AppleFormatter {
        @Override
        public String accept(Apple a) {
            return "An apple of " + a.getWeight() + "g";
        }
    }

    static class Apple{
        private String color;
        private int weight;

        public Apple(String color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public int getWeight() {
            return weight;
        }

        public static boolean isGreenApple(Apple apple) {
            return "green".equals(apple.getColor());
        }

        public static boolean isHeavyApple(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

}
