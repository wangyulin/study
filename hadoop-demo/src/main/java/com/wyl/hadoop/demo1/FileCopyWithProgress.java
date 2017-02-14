package com.wyl.hadoop.demo1;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;

/**
 * Created by wangyulin on 09/02/2017.
 */
public class FileCopyWithProgress {

    public static void main(String[] args) throws IOException {
        String localSrc = "/Users/wangyulin/word.sql";
        String dst = "hdfs://wangyulin-test-host:9000/test/hadoop/data/word.sql";
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);

        OutputStream out = fs.create(new Path(dst), () -> System.out.print("."));
        IOUtils.copyBytes(in, out, 4096, false);
    }
}
