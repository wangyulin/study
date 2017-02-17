package com.wyl.utils;

import org.apache.hadoop.io.GenericWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

/**
 * Created by wangyulin on 16/02/2017.
 */
public class MyWritable extends GenericWritable {

    public MyWritable(Writable writable) {
        set(writable);
    }

    public static Class<? extends Writable>[] CLASSES=null;

    static {
        CLASSES = (Class<? extends Writable>[])new Class[]{
                Text.class
        };
    }

    @Override
    protected Class<? extends Writable>[] getTypes() {
        return CLASSES;
    }

}
