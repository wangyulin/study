package com.wyl.common.utils.serialize;

/**
 * Created by wangyulin on 25/04/2017.
 */

public class Utils<T> {

    public static<T> T cast(Object o) {
        return (T) o;
    }

    public static<T> T cast_1(Object o, Class<T> clazz) {
        return clazz.cast(o);
    }

}
