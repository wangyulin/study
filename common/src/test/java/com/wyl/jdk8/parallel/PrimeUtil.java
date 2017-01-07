package com.wyl.jdk8.parallel;

import java.util.stream.IntStream;

/**
 * Created by wangyulin on 07/01/2017.
 */
public class PrimeUtil {

    public static void main(String[] args) {
        long b = System.currentTimeMillis ();
        long count = IntStream.range ( 1, 1000000 ).parallel ().filter ( PrimeUtil::isPrime ).count ();
        long e = System.currentTimeMillis ();
        System.out.println (count);
        System.out.println ("Speed : " + (e-b));
    }

    public static boolean isPrime(final int number) {
        int tmp = number;
        if(tmp < 2) {
            return false;
        }
        for(int i = 2; Math.sqrt ( tmp ) >= i; i++) {
            if(tmp % i == 0) {
                return false;
            }
        }
        return true;
    }
}
