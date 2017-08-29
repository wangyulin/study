package com.wyl.jvm;

/**
 * -verbose:gc -verbose:class -verbose:jni
 * -XX:+PrintGC -XX:+PrintGCDetails
 * Created by wangyulin on 4/8/16.
 * [GC (System.gc())  9339K->632K(251392K), 0.0009431 secs]
 * [Full GC (System.gc())  632K->535K(251392K), 0.0049159 secs]
 * 解读以上数据, 垃圾收集GC前后的数据剪头前后表示,括号内的数据表示对内存总容量
 */
public class ReferenceCountGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[1 * _1MB];

    public static void main(String[] args) {

        ReferenceCountGC objA = new ReferenceCountGC();
        ReferenceCountGC objB = new ReferenceCountGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();

    }

}
