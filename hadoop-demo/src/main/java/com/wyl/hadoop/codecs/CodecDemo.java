package com.wyl.hadoop.codecs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hive.serde2.thrift.TReflectionUtils;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CodecPool;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
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
        //String codecClassname = "org.apache.hadoop.io.compress.GzipCodec";
        String codecClassname = "com.hadoop.compression.lzo.LzopCodec";
        CodecPool cp;
        //compress(codecClassname, "file:///Users/wangyulin/word.sql", "file:///Users/wangyulin/word.gz");
        //uncompress(codecClassname, "file:///Users/wangyulin/word.gz", "file:///Users/wangyulin/word.gz.sql");
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("hdfs://wangyulin-test-host:9000/test/springMVC.log.gz");
        fs.delete(path);
        path = new Path("hdfs://wangyulin-test-host:9000/test/springMVC.logv2");
        fs.delete(path);
        fs.close();

        Thread.sleep(10000);
        //compress(codecClassname, "hdfs://wangyulin-test-host:9000/test/springMVC.log", "hdfs://wangyulin-test-host:9000/test/springMVC.log.gz");
        //uncompress(codecClassname, "hdfs://wangyulin-test-host:9000/test/springMVC.log.gz", "hdfs://wangyulin-test-host:9000/test/springMVC.logv2");

        compress(codecClassname, "/test/springMVC.log", "/test/springMVC.log.loz");
        //uncompress(codecClassname, "/test/springMVC.log.gz", "/test/springMVC.logv2");
        uncompressV2("/test/springMVC.log.loz", "/test/springMVC.logv3");
    }

    /**
     * 指定压缩工具类，对inputPath的文件压缩输出到outputPath
     * @param codecClassName
     * @param inputPath
     * @param outputPath
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void compress(String codecClassName, String inputPath, String outputPath) throws ClassNotFoundException, IOException {
        Class<?> codecClass = Class.forName(codecClassName);
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        FSDataOutputStream outputStream = fs.create(new Path(outputPath));
        FSDataInputStream in = fs.open(new Path(inputPath));

        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
        CompressionOutputStream out = codec.createOutputStream(outputStream);

        IOUtils.copyBytes(in, out, conf);
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }

    /**
     * 指定解压缩工具类，对inputPath的文件解压输出到outputPath
     * @param codecClassName
     * @param inputPath
     * @param outputPath
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void uncompress(String codecClassName, String inputPath, String outputPath) throws ClassNotFoundException, IOException {
        Class<?> codecClass = Class.forName(codecClassName);
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);

        FSDataInputStream inputStream = fs.open(new Path(inputPath));
        InputStream in = null;
        OutputStream out = null;

        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
        try {
            in = codec.createInputStream(inputStream);
            out = fs.create(new Path(outputPath));
            IOUtils.copyBytes(in, out, conf);
        } finally {
            IOUtils.closeStream(out);
            IOUtils.closeStream(in);
        }
    }

    /**
     * 通过指定要解压的文件扩展名获取解压工具类，对inputPath的文件解压输出到outputPath
     * @param inputPath
     * @param outputPath
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static void uncompressV2(String inputPath, String outputPath) throws ClassNotFoundException, IOException {

        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);

        CompressionCodecFactory factory = new CompressionCodecFactory(conf);
        CompressionCodec codec = factory.getCodec(new Path(inputPath));
        if(null == codec) {
            System.err.println("通过文件扩展名称获取解压类失败！");
            System.exit(1);
        }

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
