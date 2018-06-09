package com.wyl.hadoop.mr.demo1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ReadTemp {

    public static String ip = "wangyulin-test-host";
    public static String hdfsUri = String.format("hdfs://%s:9000", ip);
    public static String inputPath = String.format("hdfs://%s:9000%s", ip, "/test/noaa/data/files");
    public static String outputPath = String.format("hdfs://%s:9000%s", ip, "/test/noaa/data/temp/output/1901");

    public static Configuration initConf() {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", hdfsUri);// 改配置没有是读文件读取不到内容
        //conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        return conf;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        fs.delete(new Path(outputPath));//暴力删除原来的输出文件

        Job job = Job.getInstance(conf, "Read Temp File");

        job.setJarByClass(ReadTemp.class);
        job.setMapperClass(ReadMapper.class);
        job.setCombinerClass(WriteReduce.class);
        job.setReducerClass(WriteReduce.class);
        job.setPartitionerClass(MyPartition.class);
        job.setNumReduceTasks(5);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputDirRecursive(job, true);
        FileInputFormat.addInputPath(job, new Path(inputPath));
        //FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        // job.waitForCompletion提交任务，输入参数指定是否等待任务完成
        // 如果等待任务完成，当正常完成时返回true，反之false
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }

    public static class MyPartition extends Partitioner<Text, IntWritable> {

        @Override
        public int getPartition(Text key, IntWritable value, int numPartitions) {
            Integer year = Integer.parseInt(key.toString());
            int base = year - 1900;

            if (base >= 1 && base <= 8) {
                return 0;
            } else if (base >= 9 && base <= 16) {
                return 1;
            } else if (base >= 17 && base <= 24) {
                return 2;
            } else if (base >= 25 && base <= 32) {
                return 3;
            } else if (base >= 33 && base <= 40) {
                return 4;
            }

            return 0;
        }
    }

    public static class ReadMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        private static final int MISSING = 9999;

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String year = line.substring(15, 19);
            int airTemperature;
            if (line.charAt(87) == '+') { // parseInt doesn't like leading plus signs
                airTemperature = Integer.parseInt(line.substring(88, 92));
            } else {
                airTemperature = Integer.parseInt(line.substring(87, 92));
            }

            if(airTemperature != 9999) {
                context.write(new Text(year), new IntWritable(airTemperature));
            }
        }
    }

    public static class WriteReduce extends Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int maxTemp = Integer.MIN_VALUE;
            int minTemp = Integer.MAX_VALUE;
            for (IntWritable val : values) {
                maxTemp = (val.get() > maxTemp) ? val.get() : maxTemp;
                minTemp = (val.get() < minTemp) ? val.get() : minTemp;
                //context.write(key, val);
            }
            context.write(key, new IntWritable(maxTemp));
            context.write(key, new IntWritable(minTemp));
        }
    }

}
