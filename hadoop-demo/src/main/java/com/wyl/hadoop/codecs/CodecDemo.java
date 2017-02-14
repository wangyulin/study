package com.wyl.hadoop.codecs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hive.serde2.thrift.TReflectionUtils;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;

/**
 * Created by wangyulin on 13/02/2017.
 */
public class CodecDemo {

    public static String ip = "wangyulin-test-host";
    public static String hdfsUri = String.format("hdfs://%s:9000", ip);

    public static Configuration initConf() {
        Configuration conf = new Configuration();
        conf.set("fs.default.name", hdfsUri);//改配置没有是读文件读取不到内容
        conf.set("dfs.replication", "1");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        return conf;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String codecClassname = "org.apache.hadoop.io.compress.GzipCodec";
        //list();
        //compress(codecClassname, "file:///Users/wangyulin/word.sql", "file:///Users/wangyulin/word.gz");
        //uncompress(codecClassname, "file:///Users/wangyulin/word.gz", "file:///Users/wangyulin/word.gz.sql");

        compress(codecClassname, "hdfs://wangyulin-test-host:9000/test/springMVC.log", "hdfs://wangyulin-test-host:9000/test/springMVC.log.gz");
        uncompress(codecClassname, "hdfs://wangyulin-test-host:9000/test/springMVC.log.gz", "hdfs://wangyulin-test-host:9000/test/springMVC.logv2");
    }

    public static void list() throws IOException, InterruptedException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/test/");
        getFile(path,fs);
        fs.close();
    }

    @SuppressWarnings("deprecation")
    public static void getFile(Path path,FileSystem fs) throws IOException {
        FileStatus[] fileStatus = fs.listStatus(path);
        for(int i=0;i<fileStatus.length;i++){
            if(fileStatus[i].isDir()){
                Path p = new Path(fileStatus[i].getPath().toString());
                getFile(p,fs);
            }else{
                System.out.println(fileStatus[i].getPath().toString());
            }
        }
    }

    public static void compress(String codecClassName, String inputPath, String outputPath) throws ClassNotFoundException, IOException {
        Class<?> codecClass = Class.forName(codecClassName);
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
        FSDataOutputStream outputStream = fs.create(new Path(outputPath));
        FSDataInputStream in = fs.open(new Path(inputPath));
        CompressionOutputStream out = codec.createOutputStream(outputStream);
        IOUtils.copyBytes(in, out, conf);
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }

    public static void uncompress(String codecClassName, String inputPath, String outputPath) throws ClassNotFoundException, IOException {
        Class<?> codecClass = Class.forName(codecClassName);
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
        FSDataInputStream inputStream = fs.open(new Path(inputPath));
        InputStream in = null;
        OutputStream out = null;
        try {
            in = codec.createInputStream(inputStream);
            out = fs.create(new Path(outputPath));
            IOUtils.copyBytes(in, out, conf);
        } finally {
            IOUtils.closeStream(out);
            IOUtils.closeStream(in);
        }
    }

}
