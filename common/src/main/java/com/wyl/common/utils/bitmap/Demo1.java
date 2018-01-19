package com.wyl.common.utils.bitmap;

/**
 * Created by wangyulin on 22/12/2017.
 */
public class Demo1 {

    public static void main(String[] args) {
        int a = 100;

        String s = Integer.toBinaryString(a);
        System.out.println(s);

        int b = a << 1; //左移1位
        System.out.println(Integer.toBinaryString(b));

        int c = a >> 1;
        System.out.println(Integer.toBinaryString(c));

        int d = a>>>1; //无符号右移
        System.out.println(Integer.toBinaryString(d));

        System.out.println(Integer.toBinaryString(5>>>3));
        System.out.println(Integer.toBinaryString(-5>>3));
        System.out.println(Integer.toBinaryString(-5>>>3));

    }

}
