package com.wyl.hadoop.demo1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

/**
 * Created by wangyulin on 09/02/2017.
 */
public class ListStatus {

    public static void main(String[] args) throws IOException {
        String[] uris = {
                "/test/hadoop/data/access.log",
                "/test/hadoop/data/exposure_data_output",
                "/test/hadoop/data/mutl_input",
                "/test/hadoop/data/word.sql",
                "/test/hadoop/data/write.txt"
        };

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create("hdfs://wangyulin-test-host:9000/"), conf);
        Path[] paths = new Path[uris.length];
        for (int i = 0; i < paths.length; i++) {
            paths[i] = new Path(uris[i]);
        }

        FileStatus[] status = fs.listStatus(paths);
        for (int i = 0; i < status.length; i++) {
            System.out.println(i + " : " + status[i]);
        }

        System.out.println("-----------------------------");

        Path[] listedPaths = FileUtil.stat2Paths(status);
        for (int i = 0; i < listedPaths.length; i++) {
            System.out.println(i + " : " + listedPaths[i]);
        }

    }

}
