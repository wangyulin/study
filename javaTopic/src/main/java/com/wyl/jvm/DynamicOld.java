package com.wyl.jvm;

/**
 * -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintCommandLineFlags
 *
 * -Xms20M -Xmx20M -Xmn10M
 -XX:SurvivorRatio=8
 -XX:+PrintGCDetails
 -XX:+UseSerialGC
 -XX:+HandlePromotionFailure
 */

public class DynamicOld {

    private static final int _1M = 1024 * 1024;
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        /**
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1M / 4];
        allocation2 = new byte[_1M / 4];
        System.out.println("1");
        allocation3 = new byte[4 * _1M];
        System.out.println("2");
        allocation4 = new byte[4 * _1M];
        System.out.println("3");
        allocation4 = null;
        System.out.println("4");
        allocation4 = new byte[4 * _1M];
        System.out.println("5");

        System.out.println(allocation1);
        System.out.println(allocation2);
        System.out.println(allocation3);
        System.out.println(allocation4);
        */

        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7,
                allocation8;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
    }

}
