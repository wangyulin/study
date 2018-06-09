package com.wyl.hadoop.mr.demo2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MySortJobV2 {

    public static String ip = "wangyulin-test-host";
    public static String hdfsUri = String.format("hdfs://%s:9000", ip);
    public static String inputPath = String.format("hdfs://%s:9000%s", ip, "/test/mr/demo2/input/");
    public static String outputPath = String.format("hdfs://%s:9000%s", ip, "/test/mr/demo2/output");

    public static Configuration initConf() {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", hdfsUri);// 改配置没有是读文件读取不到内容
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        return conf;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        fs.delete(new Path(outputPath));//暴力删除原来的输出文件

        Job job = Job.getInstance(conf, "SortNumberV2");
        job.setJarByClass(MySortJobV2.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        job.setMapOutputKeyClass(MyNewKey.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setNumReduceTasks(1);

        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(LongWritable.class);

        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

    public static class MyMapper extends Mapper<LongWritable, Text, MyNewKey, LongWritable> {

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] spilted = value.toString().split("\t");
            long firstNum = Long.parseLong(spilted[0]);
            long secondNum = Long.parseLong(spilted[1]);
            MyNewKey myNewKey = new MyNewKey(firstNum, secondNum);
            context.write(myNewKey, new LongWritable(secondNum));
        }
    }

    public static class MyReducer extends Reducer<MyNewKey, LongWritable, LongWritable, LongWritable> {

        @Override
        protected void reduce(MyNewKey key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
            context.write(new LongWritable(key.firstNum), new LongWritable(key.secondNum));
        }
    }

    public static class MyNewKey implements WritableComparable<MyNewKey> {

        long firstNum;
        long secondNum;

        public MyNewKey() {
        }

        public MyNewKey(long first, long second) {
            firstNum = first;
            secondNum = second;
        }

        @Override
        public int compareTo(MyNewKey anotherKey) {
            long min = firstNum - anotherKey.firstNum;
            if (min != 0) {
                // 说明第一列不相等，则返回两数之间小的数
                return (int) min;
            } else {
                return (int) (secondNum - anotherKey.secondNum);
            }
        }

        @Override
        public void write(DataOutput out) throws IOException {
            out.writeLong(firstNum);
            out.writeLong(secondNum);
        }

        @Override
        public void readFields(DataInput in) throws IOException {
            firstNum = in.readLong();
            secondNum = in.readLong();
        }
    }
}
