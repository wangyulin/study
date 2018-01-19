package com.wyl.common.utils.base;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Created by wangyulin on 28/12/2017.
 */
@State(Scope.Benchmark)
public class LoopBenchmarkMain {
    volatile int size = 1000000;
    volatile List<Integer> integers = null;

    public static void main(String[] args) {
        LoopBenchmarkMain benchmark = new LoopBenchmarkMain();
        benchmark.setup();

        System.out.println("iteratorMaxInteger max is: " + benchmark.iteratorMaxInteger());
        System.out.println("forEachLoopMaxInteger max is: " + benchmark.forEachLoopMaxInteger());
        System.out.println("forEachLambdaMaxInteger max is: " + benchmark.forEachLambdaMaxInteger());
        System.out.println("forMaxInteger max is: " + benchmark.forMaxInteger());
        System.out.println("parallelStreamMaxInteger max is: " + benchmark.parallelStreamMaxInteger());
        System.out.println("streamMaxInteger max is: " + benchmark.streamMaxInteger());
        System.out.println("iteratorMaxInteger max is: " + benchmark.lambdaMaxInteger());
    }

    @Setup
    public void setup() {
        integers = new ArrayList<Integer>(size);
        populate(integers);
    }

    public void populate(List<Integer> list) {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(1000000));
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(2)
    @Measurement(iterations = 5)
    @Warmup(iterations = 5)
    public int iteratorMaxInteger() {
        long startTime = new Date().getTime();
        int max = Integer.MIN_VALUE;
        for (Iterator<Integer> it = integers.iterator(); it.hasNext(); ) {
            max = Integer.max(max, it.next());
        }
        System.out.println("====iteratorMaxInteger : " + (new Date().getTime() - startTime));
        return max;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(2)
    @Measurement(iterations = 5)
    @Warmup(iterations = 5)
    public int forEachLoopMaxInteger() {
        long startTime = new Date().getTime();
        int max = Integer.MIN_VALUE;
        for (Integer n : integers) {
            max = Integer.max(max, n);
        }
        System.out.println("====forEachLoopMaxInteger : " + (new Date().getTime() - startTime));
        return max;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(2)
    @Measurement(iterations = 5)
    @Warmup(iterations = 5)
    public int forEachLambdaMaxInteger() {
        long startTime = new Date().getTime();
        final Wrapper wrapper = new Wrapper();
        wrapper.inner = Integer.MIN_VALUE;

        integers.forEach(i -> helper(i, wrapper));
        System.out.println("====forEachLambdaMaxInteger : " + (new Date().getTime() - startTime));
        return wrapper.inner.intValue();
    }

    public static class Wrapper {
        public Integer inner;
    }

    private int helper(int i, Wrapper wrapper) {
        wrapper.inner = Math.max(i, wrapper.inner);
        return wrapper.inner;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(2)
    @Measurement(iterations = 5)
    @Warmup(iterations = 5)
    public int forMaxInteger() {
        long startTime = new Date().getTime();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            max = Integer.max(max, integers.get(i));
        }
        System.out.println("====forMaxInteger : " + (new Date().getTime() - startTime));
        return max;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(2)
    @Measurement(iterations = 5)
    @Warmup(iterations = 5)
    public int parallelStreamMaxInteger() {
        long startTime = new Date().getTime();
        //Optional<Integer> max = integers.parallelStream().reduce(Integer::max);
        int max = integers.parallelStream().mapToInt(Integer::intValue).reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("====parallelStreamMaxInteger : " + (new Date().getTime() - startTime));
        return max;
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(2)
    @Measurement(iterations = 5)
    @Warmup(iterations = 5)
    public int streamMaxInteger() {
        long startTime = new Date().getTime();
        Optional<Integer> max = integers.stream().reduce(Integer::max);
        System.out.println("====streamMaxInteger : " + (new Date().getTime() - startTime));
        return max.get();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(2)
    @Measurement(iterations = 5)
    @Warmup(iterations = 5)
    public int lambdaMaxInteger() {
        long startTime = new Date().getTime();
        int res = integers.stream().reduce(Integer.MIN_VALUE, (a, b) -> Integer.max(a, b));
        System.out.println("====lambdaMaxInteger : " + (new Date().getTime() - startTime));
        return res;
    }
}