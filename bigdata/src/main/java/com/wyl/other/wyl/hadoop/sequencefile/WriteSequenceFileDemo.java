package com.wyl.other.wyl.hadoop.sequencefile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;

/**
 * Created by wangyulin on 17/02/2017.
 */
public class WriteSequenceFileDemo {

    public static String ip = "wangyulin-test-host";
    public static String hdfsUri = String.format("hdfs://%s:9000", ip);

    public static Configuration initConf() {
        Configuration conf = new Configuration();
        conf.set("fs.default.name", hdfsUri);//改配置没有是读文件读取不到内容
        conf.set("dfs.replication", "1");
        return conf;
    }

    private static final String[] DATA = {
        "One, two, buckle my shoe",
        "Three, four, shut the door",
        "Five, six, pick up sticks",
        "Seven, eight, lay them straight",
        "Nine, ten, a big fat hen"
    };

    public static void main(String[] args) throws IOException {
        String uri = "/test/out.seq";//"file:///Users/wangyulin/out";

        writeSeqFile(uri);
        System.out.println("==================================");
        readSeqFile(uri);
    }

    public static void writeSeqFile(String uri) throws IOException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(uri);

        IntWritable key = new IntWritable();
        Text value = new Text();
        SequenceFile.Writer writer = null;

        try {
            writer = SequenceFile.createWriter(fs, conf, path,
                    key.getClass(), value.getClass());
            for(int i = 0; i < 100; i++) {
                key.set(100 - i);
                value.set(DATA[i % DATA.length]);
                System.out.printf("[%s]\t%s\t%s\n", writer.getLength(), key, value);
                writer.append(key, value);
            }

        } finally {
            IOUtils.closeStream(writer);
        }
    }

    public static void readSeqFile(String uri) throws IOException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(uri);

        SequenceFile.Reader reader = null;
        try{
            reader = new SequenceFile.Reader(fs, path, conf);

            Writable key = (Writable)
                    ReflectionUtils.newInstance(reader.getKeyClass(), conf);
            Writable value = (Writable)
                    ReflectionUtils.newInstance(reader.getValueClass(), conf);

            long position = reader.getPosition();
            while(reader.next(key, value)) {
                String syncSeen = reader.syncSeen() ? "*" : "";
                System.out.printf("[%s%s]\t%s\t%s\n", position, syncSeen, key, value);
                position = reader.getPosition();
            }
        } finally {
            IOUtils.closeStream(reader);
        }

    }
}
