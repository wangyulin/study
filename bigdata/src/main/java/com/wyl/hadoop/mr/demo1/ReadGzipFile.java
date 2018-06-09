package com.wyl.hadoop.mr.demo1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ReadGzipFile {

    public static String ip = "wangyulin-test-host";
    public static String hdfsUri = String.format("hdfs://%s:9000", ip);
    public static String inputPath = String.format("hdfs://%s:9000%s", ip, "/test/noaa/data/files/1901");
    public static String outputPath = String.format("hdfs://%s:9000%s", ip, "/test/noaa/data/output/1901");

    public static Configuration initConf() {
        Configuration conf = new Configuration();
        //conf.set("mapred.jop.tracker", "hdfs://10.235.152.23:9001");
        conf.set("fs.defaultFS", hdfsUri);// 改配置没有是读文件读取不到内容
        conf.set("dfs.replication", "1");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        return conf;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        fs.delete(new Path(outputPath));//暴力删除原来的输出文件

        Job job = Job.getInstance(conf, "Read Gzip File");
        job.setJarByClass(ReadGzipFile.class);
        job.setMapperClass(ReadGzipFile.ReadMapper.class);
        job.setReducerClass(ReadGzipFile.WriteReduce.class);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        // job.waitForCompletion提交任务，输入参数指定是否等待任务完成
        // 如果等待任务完成，当正常完成时返回true，反之false
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static class ReadMapper extends Mapper<LongWritable, Text, NullWritable, Text> {

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            context.write(NullWritable.get(), value);
        }
    }

    public static class WriteReduce extends Reducer<NullWritable, Text, NullWritable, Text> {

        @Override
        protected void reduce(NullWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for (Text val : values) {
                context.write(NullWritable.get(), val);
            }
        }
    }

}
