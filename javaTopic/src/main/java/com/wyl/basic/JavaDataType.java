package com.wyl.basic;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class JavaDataType {

    public static void main(String[] args) {
        String c1 = "ab";
        String c2 = "abc";
        String c3 = c1 + "c";
        System.out.println(c2 == c3);
        System.out.println(c2.equals(c3));

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);



        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memorymbean.getHeapMemoryUsage();
        System.out.println(memorymbean.getNonHeapMemoryUsage());

        System.out.println("INIT HEAP: " + usage.getInit());
        System.out.println("MAX HEAP: " + usage.getMax());
        System.out.println("USE HEAP: " + usage.getUsed());
        System.out.println("\nFull Information:");
        System.out.println("Heap Memory Usage: "
                + memorymbean.getHeapMemoryUsage());
        System.out.println("Non-Heap Memory Usage: "
                + memorymbean.getNonHeapMemoryUsage());
    }

}
