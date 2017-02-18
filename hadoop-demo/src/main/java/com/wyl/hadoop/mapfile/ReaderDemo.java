package com.wyl.hadoop.mapfile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;

import java.io.IOException;

/**
 * Created by wangyulin on 18/02/2017.
 */
public class ReaderDemo {

    public static String ip = "wangyulin-test-host";
    public static String hdfsUri = String.format("hdfs://%s:9000", ip);

    public static Configuration initConf() {
        Configuration conf = new Configuration();
        conf.set("fs.default.name", hdfsUri);//改配置没有是读文件读取不到内容
        conf.set("dfs.replication", "1");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        return conf;
    }

    public static void main(String[] args) throws IOException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);

        Path outputFile = new Path("/test/springMVC.map");

        Text txtKey = new Text();
        Text txtValue = new Text();

        MapFile.Writer writer = null;

        try {
            writer = new MapFile.Writer(conf, fs, outputFile.toString(),
                    txtKey.getClass(), txtValue.getClass());
            writer.append(new Text("key"), new Text("value"));
        } finally {
            IOUtils.closeStream(writer);
            System.out.println("Map file created successfully!!");
        }
    }

}
