package com.wyl.jvm;

import java.util.concurrent.TimeUnit;

/**
 * -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+HeapDumpOnOutOfMemoryError
 */

public class TestJstat {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {

        byte[][] array = new byte[1024][];
        int index = 0;

        while(index < 1024) {
            TimeUnit.SECONDS.sleep(5);
            array[index++] = new byte[_1MB];
        }

        System.out.println("End of the program");

    }

}
