package com.wyl.hadoop.writecollection;

import org.apache.hadoop.io.ArrayPrimitiveWritable;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;

/**
 * Created by wangyulin on 17/02/2017.
 */
public class ArrayWritableDemo {

    public static void main(String[] args) {
        ArrayWritable writable = new ArrayWritable(Text.class);
        Text t1 = new Text("AAA");
        Text t2 = new Text("BBB");



        ArrayPrimitiveWritable arrayPrimitiveWritable = new ArrayPrimitiveWritable(boolean.class);
        arrayPrimitiveWritable.set(new boolean[]{true, false});

        System.out.println(((boolean[])arrayPrimitiveWritable.get())[1]);
    }

}
