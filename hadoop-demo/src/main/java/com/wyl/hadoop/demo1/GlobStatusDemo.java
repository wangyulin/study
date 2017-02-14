package com.wyl.hadoop.demo1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

import java.io.IOException;

/**
 * Created by wangyulin on 09/02/2017.
 */
public class GlobStatusDemo {

    public static String ip = "wangyulin-test-host";
    public static String hdfsUri = String.format("hdfs://%s:9000", ip);

    public static Configuration initConf() {
        Configuration conf = new Configuration();
        conf.set("fs.default.name", hdfsUri);
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        return conf;
    }

    public static void main(String[] args) throws IOException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        FileStatus[] status = fs.globStatus(new Path("/test/hadoop/data/*"),
                new RegexExcludePathFilter("/test/hadoop/data/*.sql"));
        for (FileStatus one : status) {
            System.out.println(one);
        }
    }

}

class RegexExcludePathFilter implements PathFilter {

    private final String regex;

    public RegexExcludePathFilter(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean accept(Path path) {
        return path.toString().matches(regex);
    }
}
