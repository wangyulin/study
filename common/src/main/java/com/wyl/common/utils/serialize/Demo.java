package com.wyl.common.utils.serialize;

import org.apache.commons.lang.SerializationUtils;

/**
 * Created by wangyulin on 25/04/2017.
 */
public class Demo<T> {

    public static void main(String[] args) throws ClassNotFoundException {
        test1();

    }

    public T deserialize(byte[] bytes) throws ClassNotFoundException {
        T obj = (T) SerializationUtils.deserialize(bytes);
        return obj;

    }

    public static void test1() throws ClassNotFoundException {

    }

}
