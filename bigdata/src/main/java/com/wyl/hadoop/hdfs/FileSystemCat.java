package com.wyl.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by wangyulin on 09/02/2017.
 */
public class FileSystemCat {

    public static void main(String[] args) throws IOException {
        String uri = "hdfs://wangyulin-test-host:9000/test/hadoop/data/weather_data_output_2/part-r-00000";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        FileStatus status = fs.getFileStatus(new Path(uri));


        /** FSDataInputStream 支持随机读*/
        FSDataInputStream in = null;
        try {
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 8, false);
            System.out.println("--------------------");
            in.seek(0);
            IOUtils.copyBytes(in, System.out, 8, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }

}
