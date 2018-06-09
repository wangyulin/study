package com.wyl.other.wyl.hadoop.mapfile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyulin on 18/02/2017.
 */
public class ReaderDemo {

    public static String ip = "wangyulin-test-host";
    public static String hdfsUri = String.format("hdfs://%s:9000", ip);

    private static Map<String, String> data = new HashMap();

    private static final String [] myValue = {
            "hello world",
            "bye world",
            "hello hadoop",
            "bye hadoop"
    };

    public static Configuration initConf() {
        Configuration conf = new Configuration();
        conf.set("fs.default.name", hdfsUri);//改配置没有是读文件读取不到内容
        conf.set("dfs.replication", "1");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("io.map.index.interval", "128");
        return conf;
    }

    public static void main(String[] args) throws IOException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        //setData();
        String pathStr = "/test/springMVC.map";
        //writeMapFIle(conf, fs, pathStr);
        readMapFile(conf, fs, pathStr);
    }

    public static void readMapFile(Configuration conf, FileSystem fs, String pathStr) throws IOException {
        Path path = new Path(pathStr);

        IntWritable intKey = new IntWritable();
        Text txtValue = new Text();

        MapFile.Reader reader = null;
        try {
            reader = new MapFile.Reader(fs, pathStr, conf);
            WritableComparable key =
                    (WritableComparable)ReflectionUtils.newInstance(reader.getKeyClass(), conf);
            Writable value =
                    (Writable) ReflectionUtils.newInstance(reader.getValueClass(), conf);
            while (reader.next(key, value)) {
                System.out.printf("%s\t%s\n", key, value);
            }
            reader.get(new IntWritable(7), value);
        } finally {
            IOUtils.closeStream(reader);
        }
    }

    public static void writeMapFIle(Configuration conf, FileSystem fs, String path) throws IOException {
        Path outputFile = new Path(path);

        IntWritable txtKey = new IntWritable();
        Text txtValue = new Text();

        MapFile.Writer writer = null;

        try {
            writer = new MapFile.Writer(conf, fs, outputFile.toString(),
                    txtKey.getClass(), txtValue.getClass());
            /*for (String key : data.keySet()) {
                writer.append(new Text(key), new Text(data.get(key)));
                System.out.println("key : " + key);
            }*/

            for (int i = 0; i < 500; i++) {
                writer.append(new IntWritable(i), new Text(myValue[i % myValue.length]));
            }
            //writer.append(new Text("key"), new Text("value"));
        } finally {
            IOUtils.closeStream(writer);
            System.out.println("Map file created successfully!!");
        }
    }

    public static void setData() {
        data.put("A", "aa");
        data.put("ABC", "abc");
        data.put("ABB", "abb");
        data.put("ABF", "abf");
        data.put("ABR", "abr");
    }

}
