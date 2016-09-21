package com.wyl.hadoop.demo1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class Hadoop_LS {

	public static String ip = "10.235.152.23";
	public static String hdfsUri = String.format("hdfs://%s:9000", ip);

	public static Configuration initConf() {
		Configuration conf = new Configuration();
		// conf.set("mapred.jop.tracker", "hdfs://10.235.152.23:9001");
		conf.set("fs.default.name", hdfsUri);//改配置没有是读文件读取不到内容
		conf.set("dfs.replication", "1");
		return conf;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		//put();
		//list();
		//getHDFSNode();
		//getHDFSNode1();
		//createDir();
		//deleDir();
		//writeFile();
		readFile();
		//uploadFile();
	}
	
	public static void uploadFile() throws IOException {
		Configuration conf = initConf();  
        FileSystem fs = FileSystem.get(conf);  
        Path src = new Path("/Users/wangyulin/workDir/miui/study/serverDemo/log/access.log");  
        Path dst = new Path("/test/hadoop/data/");  
        fs.copyFromLocalFile(src, dst);  
        fs.close();
	}
	
	public static void readFile() throws IOException {
		Configuration conf = initConf();  
        FileSystem fs = FileSystem.get(conf);  
        Path path = new Path("/test/hadoop/data/write.txt");  
          
        if(fs.exists(path)){  
            FSDataInputStream is = fs.open(path);  
            FileStatus status = fs.getFileStatus(path);  
            byte[] buffer = new byte[Integer.parseInt(String.valueOf(status.getLen()))];  
            is.readFully(0, buffer);  
            is.close();  
            fs.close();  
            System.out.println(new String(buffer));  
        }
	}
	
	public static void writeFile() throws IOException {
		Configuration conf = initConf();  
        FileSystem fs = FileSystem.get(conf);  
        Path path = new Path("/test/hadoop/data/write.txt");  
        FSDataOutputStream out = fs.create(path);  
        out.writeUTF("da jia hao,cai shi zhen de hao!");  
        fs.close();  
	}
	
	@SuppressWarnings("deprecation")
	public static void deleDir() throws IOException {
		Configuration conf = initConf();  
        FileSystem fs = FileSystem.get(conf);  
        Path path = new Path("/test/hadoop/data/20160921");  
        fs.delete(path);
        fs.close();
	}
	
	public static void createDir() throws IOException {
		Configuration conf = initConf();  
        FileSystem fs = FileSystem.get(conf);  
        Path path = new Path("/test/hadoop/data/20160921");  
        fs.create(path);  
        fs.close(); 
	}

	/** 上传本地文件到HDFS */
	public static void put() throws IOException, InterruptedException {
		Configuration conf = initConf();
		FileSystem hdfs = FileSystem.get(URI.create(hdfsUri), conf, "hadoop");

		// HDFS位置
		Path dst = new Path(String.format("%s/test/springMVC.log", hdfsUri));

		hdfs.exists(dst);
		File file = new File("/Users/wangyulin/workDir/miui/springMVC/log/springMVC.log");

		InputStream in = new BufferedInputStream(new FileInputStream(file));

		OutputStream out = hdfs.create(dst, new Progressable() {
			public void progress() {
				System.out.print(".");
			}
		});
		IOUtils.copyBytes(in, out, 4096, true);
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
	
	/** 
     * HDFS集群上所有节点名称信息 
     * @Title:   
     * @Description:  
     * @param  
     * @return  
     * @throws 
     */  
    public static void getHDFSNode() throws IOException{  
        Configuration conf = initConf();  
        FileSystem fs = FileSystem.get(conf);
  
        DistributedFileSystem  dfs = (DistributedFileSystem)fs;  
        DatanodeInfo[] dataNodeStats = dfs.getDataNodeStats();  
          
        for(int i=0;i<dataNodeStats.length;i++){  
            System.out.println("DataNode_" + i + "_Node:" + dataNodeStats[i].getHostName());  
        }  
    }
    
    /** 
     * HDFS集群上所有节点名称信息 
     * @Title:   
     * @Description:  
     * @param  
     * @return  
     * @throws 
     */  
    public static void getHDFSNode1() throws IOException{  
        Configuration conf = initConf();  
        FileSystem fs = FileSystem.get(conf);  
  
        DistributedFileSystem  dfs = (DistributedFileSystem)fs;  
        DatanodeInfo[] dataNodeStats = dfs.getDataNodeStats();  
          
        for(int i=0;i<dataNodeStats.length;i++){  
            System.out.println("DataNode_" + i + "_Node:" + dataNodeStats[i].getHostName());  
        }         
    }
}
