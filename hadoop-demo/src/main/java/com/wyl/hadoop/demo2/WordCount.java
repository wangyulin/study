package com.wyl.hadoop.demo2;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

	public static String ip = "wangyulin-test-host";
	public static String hdfsUri = String.format("hdfs://%s:9000", ip);
	public static String inputPath = String.format("hdfs://%s:9000%s", ip, "/user/joe/wordcount/input");
	public static String outputPath = String.format("hdfs://%s:9000%s", ip, "/user/joe/wordcount/output");

	public static Configuration initConf() {
		Configuration conf = new Configuration();
		//conf.set("mapred.jop.tracker", "hdfs://10.235.152.23:9001");
		conf.set("fs.default.name", hdfsUri);// 改配置没有是读文件读取不到内容
		conf.set("dfs.replication", "1");
		conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
		return conf;
	}

	// Mapper<输入key, 输入value, 输出key, 输出value>
	// Hadoop默认使用TextInputFormat（本例也是）
	// TextInputFormat按行对文件进行分割，并传给Mapper
	// 输入key表示当前行的第一个byte是整个文件的第几个byte，实际是LongWritable类型
	// 这里用Object是为了通用性考虑，完全可以换成LongWritable
	public static class TokenizerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			// 对文本分词（tokenizing）
			StringTokenizer itr = new StringTokenizer(value.toString());
			System.out.println("key : " + key + " ;" + " value : " + value);
			while (itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				// context可以简单看作是mapper运行环境的封装（存储输出等）
				context.write(word, one);
			}
		}
	}

	// Reducer<输入key, 输入value, 输出key, 输出value>
	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		Thread.State state;
		// 用来生成一些MapReduce的默认参数
		Configuration conf = initConf();
		FileSystem fs = FileSystem.get(conf);
		fs.delete(new Path(outputPath));
		
		Job job = Job.getInstance(conf, "word count");

		// 找到包含WordCount这个class的jar包
		// 从而告诉各个node要在哪找这个job需要的mapper跟reducer函数
		job.setJarByClass(WordCount.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setNumReduceTasks(1);
		// 设置输出key-value对的类别
		job.setOutputKeyClass(Text.class);

		// IntWritable的含义是，Hadoop实现了更加符合Hadoop机制的对int序列化的方法
		job.setOutputValueClass(IntWritable.class);

		// InputFormat包含对input的描述和处理方法
		// FileInputFormat是所有基于文件的InputFormat的基类
		// InputFormat的类一般会提供一些对job的input进行操作的函数
		// 这里用FileInputFormat是为了保证通用性
		FileInputFormat.addInputPath(job, new Path(inputPath));

		FileOutputFormat.setOutputPath(job, new Path(outputPath));

		// job.waitForCompletion提交任务，输入参数指定是否等待任务完成
		// 如果等待任务完成，当正常完成时返回true，反之false
		//System.exit(job.waitForCompletion(true) ? 0 : 1);
		boolean result = job.waitForCompletion(true);
		System.out.println("MapReduce 执行结果 :" + result);
	}
}