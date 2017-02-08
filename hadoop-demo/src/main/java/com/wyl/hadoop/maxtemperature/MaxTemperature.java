package com.wyl.hadoop.maxtemperature;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by wangyulin on 07/02/2017.
 */
public class MaxTemperature {

    public static String ip = "wangyulin-test-host";
    public static String hdfsUri = String.format("hdfs://%s:9000", ip);
    public static String inputPath = String.format("hdfs://%s:9000%s", ip, "/test/hadoop/data/weather_data/noaa/*");
    public static String outputPath = String.format("hdfs://%s:9000%s", ip, "/test/hadoop/data/weather_data_output");

    public static Configuration initConf() {
        Configuration conf = new Configuration();
        //conf.set("mapred.jop.tracker", "hdfs://10.235.152.23:9001");
        conf.set("fs.default.name", hdfsUri);// 改配置没有是读文件读取不到内容
        conf.set("dfs.replication", "1");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        return conf;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        fs.delete(new Path(outputPath));

        Job job = Job.getInstance(conf, "MaxTemperature");

        job.setJarByClass(MaxTemperature.class);

        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        job.setMapperClass(MaxTemperatureMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setNumReduceTasks(1);

        System.exit(job.waitForCompletion(false) ? 0 : 1);
    }

}
