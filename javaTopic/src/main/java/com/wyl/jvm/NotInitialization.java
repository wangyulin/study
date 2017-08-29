package com.wyl.jvm;

/**
 *  -XX:+TraceClassLoading
 *  SubClass在HotSpot JVM中只会加载,不会初始化
 * Created by wangyulin on 4/12/16.
 */
class SuperClass {

    static {
        System.out.println("SuperClass init !");
    }

    public static int value = 1;

}

class SubClass extends SuperClass{

    static {
        System.out.println("SubClass init ! ");
    }

}

class ConstClass  {
    static {
        System.out.println("ConstClass init !");
    }

    public static final String HELLOWORLD = "hello world";
}

public class NotInitialization{

    public static void main(String[] args) {
        //1
        //System.out.println(SubClass.value);

        //2
        //SuperClass[] sc = new SuperClass[10];

        //3
        System.out.println(ConstClass.HELLOWORLD);
    }

}
