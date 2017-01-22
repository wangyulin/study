package com.wyl.comment.utils;


import org.apache.tomcat.util.codec.binary.Base64;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wangyulin on 21/01/2017.
 */
public class DecodeUtils {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        String str = "123456";

        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method encode= clazz.getMethod("encode", byte[].class);
        encode.setAccessible(true);
        Object retObj=encode.invoke(null, new Object[]{str.getBytes()});

        System.out.println(retObj);

        Method decode= clazz.getMethod("decode", String.class);
        decode.setAccessible(true);
        Object res=decode.invoke(null, retObj.toString());

        System.out.println(res);
    }

}
