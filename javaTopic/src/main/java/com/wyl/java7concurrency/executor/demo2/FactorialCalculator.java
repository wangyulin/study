package com.wyl.java7concurrency.executor.demo2;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 24/08/2017.
 */
public class FactorialCalculator implements Callable<Integer> {

    private Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if(number == 0 || number == 1) {
            return result;
        } else {
            for (int i = 2; i <= number; i++) {
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.printf("%s: %d\n", Thread.currentThread().getName(), result);
        return result;
    }
}
