package com.wyl.other.wyl.hadoop.demo2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MutlInput {

	public static String ip = "wangyulin-test-host";
	public static String hdfsUri = String.format("hdfs://%s:9000", ip);
	public static String inputPath = String.format("hdfs://%s:9000%s", ip, "/test/hadoop/data/mutl_input");
	public static String outputPath = String.format("hdfs://%s:9000%s", ip, "/test/hadoop/data/mutl_output");

	public static Configuration initConf() {
		Configuration conf = new Configuration();
		conf.set("fs.default.name", hdfsUri);// 改配置没有是读文件读取不到内容
		conf.set("dfs.replication", "1");
		return conf;
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		// 用来生成一些MapReduce的默认参数
		Configuration conf = initConf();
		FileSystem fs = FileSystem.get(conf);
		fs.delete(new Path(outputPath));
		
		Job job = Job.getInstance(conf, "Mutl Input File");
		job.setJarByClass(MutlInput.class);

		job.setMapperClass(PersonAddrMap.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Bean.class);

		job.setReducerClass(PersonAddreRedu.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		job.waitForCompletion(true);
	}

}

class PersonAddreRedu extends Reducer<IntWritable, Bean, NullWritable, Text> {
	@Override
	protected void reduce(IntWritable key, Iterable<Bean> values,
			Context context) throws IOException, InterruptedException {
		Bean Addre = null;
		List<Bean> peoples = new ArrayList<Bean>();
		/*
		 * 如果values的第一个元素信息就是地址Addre的信息的话,
		 * 我们就不再需要一个List来缓存person信息了,values后面的全是人员信息 将减少巨大的内存空间
		 * 
		 * partitioner和shuffer的过程：
		 * partitioner的主要功能是根据reduce的数量将map输出的结果进行分块,将数据送入到相应的reducer.
		 * 所有的partitioner都必须实现partitioner接口并实现getPartition方法,该方法的返回值为int类型，
		 * 并且取值范围在0~(numOfReducer-1), 从而能将map的输出输入到对应的reducer中,对于某个mapreduce过程,
		 * hadoop框架定义了默认的partitioner为HashPartioner,
		 * 该partitioner使用key的hashCode来决定将该key输送到哪个reducer;
		 * shuffle将每个partitioner输出的结果根据key进行group以及排序,
		 * 将具有相同key的value构成一个values的迭代器,并根据key进行排序分别调用
		 * 开发者定义的reduce方法进行排序,因此mapreducer的所以key必须实现comparable接口的compareto()
		 * 方法从而能实现两个key对象的比较
		 * 
		 * 我们需要自定义key的数据结构(shuffle按照key进行分组)来满足共同addreNo的情况下地址表的更小需求
		 */
				
		for (Bean bean : values) {
			System.out.println(bean);
			if (bean.getFlag() == 0) { // 表示地区表
				Addre = new Bean(bean);

			} else {
				peoples.add(new Bean(bean)); // 添加到peoplelist中
			}
		}
		System.out.println("-----------------------------");
		for (Bean peo : peoples) { // 给peoplelist添加地区名字
			peo.setAddreName(Addre.getAddreName());
			context.write(NullWritable.get(), new Text(peo.toString()));
		}
	}
}

class PersonAddrMap extends Mapper<LongWritable, Text, IntWritable, Bean> {
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String str[] = line.split("\t");
		if (str.length == 2) { // 地区信息表
			Bean bean = new Bean();
			bean.setAddreNo(str[0]);
			bean.setAddreName(str[1]);
			bean.setFlag(0); // 0表示地区
			context.write(new IntWritable(Integer.parseInt(str[0])), bean);
		} else {// 人员信息表
			Bean bean = new Bean();
			bean.setUserNo(str[0]);
			bean.setUserName(str[1]);
			bean.setAddreNo(str[2]);
			bean.setFlag(1); // 1表示人员表
			context.write(new IntWritable(Integer.parseInt(str[2])), bean);
		}
	}
}

/*
 * 人员和地址的通用bean
 */
class Bean implements WritableComparable<Bean> {
	private String userNo = "";
	private String userName = "";
	private String addreNo = "";
	private String addreName = "";
	private int flag;

	public Bean(Bean bean) {
		this.userName = bean.getUserName();
		this.userNo = bean.getUserNo();
		this.addreName = bean.getAddreName();
		this.addreNo = bean.getAddreNo();
		this.flag = bean.getFlag();
	}

	public Bean() {
		super();
	}

	public Bean(String userNo, String userName, String addreNo, String addreName, int flag) {
		super();
		this.userNo = userNo;
		this.userName = userName;
		this.addreNo = addreNo;
		this.addreName = addreName;
		this.flag = flag;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddreNo() {
		return addreNo;
	}

	public void setAddreNo(String addreNo) {
		this.addreNo = addreNo;
	}

	public String getAddreName() {
		return addreName;
	}

	public void setAddreName(String addreName) {
		this.addreName = addreName;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(userNo);
		out.writeUTF(userName);
		out.writeUTF(addreNo);
		out.writeUTF(addreName);
		out.writeInt(flag);

	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.userNo = in.readUTF();
		this.userName = in.readUTF();
		this.addreNo = in.readUTF();
		this.addreName = in.readUTF();
		this.flag = in.readInt();

	}

	@Override
	public int compareTo(Bean arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "userNo=" + userNo + ", userName=" + userName + ", addreNo=" + addreNo + ", addreName=" + addreName;
	}
}