/*
package com.wyl.other.wyl.hadoop.serialization;

import com.wyl.utils.MyWritable;
import com.wyl.utils.WritableUtils;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

*/
/**
 * Created by wangyulin on 16/02/2017.
 *//*

public class TestObjectWritable {

    public static void main(String[] args) throws IOException {
        Text text = new Text("\u0041\u0071");
        ObjectWritable objectWritable = new ObjectWritable(text);
        System.out.println(StringUtils.byteToHexString(WritableUtils.serialize(objectWritable)));

        MyWritable myWritable=new MyWritable(text);
        System.out.println(StringUtils.byteToHexString(WritableUtils.serialize(myWritable)));
    }

}
*/
