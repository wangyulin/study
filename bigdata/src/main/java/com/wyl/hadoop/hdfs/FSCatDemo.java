package com.wyl.hadoop.hdfs;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by wangyulin on 09/02/2017.
 */
public class FSCatDemo {

    static {
        /**
         * 为了让java程序能够识别hadoop的hdfs url需要配置额外的URLStreamHandlerFactory
         * 如下方法java虚拟机只能调用一次，若原有的其他程序已经声明过该factory，则我的java程序将无法从hadoop中读取数据
         */
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args) throws Exception {

        InputStream in = null;
        try{
            in = new URL("hdfs://wangyulin-test-host:9000/test/hadoop/data/weather_data_output_2/part-r-00000").openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }

}
