package com.wyl.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

public class HDFSBaseDemo {

    public static String ip = "wangyulin-test-host";
    public static String hdfsUri = String.format("hdfs://%s:9000", ip);

    public static Configuration initConf() {
        Configuration conf = new Configuration();
        // conf.set("mapred.jop.tracker", "hdfs://10.235.152.23:9001");
        conf.set("fs.default.name", hdfsUri);//改配置没有是读文件读取不到内容
        conf.set("dfs.replication", "1");
        return conf;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //deleDir();
        //globStatus("/test/hadoop/");
        uploadFile("/Users/wangyulin/workdir/ncdc_data/files/","/test/noaa/data/");
        //list();
        //getHDFSNode();
        //readFileV2("/test/springMVC.log");
    }

    public static void list() throws IOException, InterruptedException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/test/");
        getFile(path,fs);
        fs.close();
    }

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

    /** 上传本地文件到HDFS */
    public static void put() throws IOException, InterruptedException {
        Configuration conf = initConf();
        FileSystem hdfs = FileSystem.get(URI.create(hdfsUri), conf, "hadoop");

        // HDFS位置
        Path dst = new Path(String.format("/test/hadoop/data/word_2.sql", hdfsUri));

        hdfs.exists(dst);
        File file = new File("/Users/wangyulin/workDir/word.sql");

        InputStream in = new BufferedInputStream(new FileInputStream(file));

        OutputStream out = hdfs.create(dst, () -> System.out.print("."));
        IOUtils.copyBytes(in, out, 4096, true);
    }

    /**
     * 从本地拷贝文件或者目录到hdfs
     * @param localFrom
     * @param dfsTo
     * @throws IOException
     */
    public static void uploadFile(String localFrom, String dfsTo) throws IOException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        Path src = new Path(localFrom);
        Path dst = new Path(dfsTo);
        fs.copyFromLocalFile(src, dst);
        fs.close();
    }

    public static void globStatus(String path) throws IOException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        FileStatus[] res = fs.globStatus(new Path(path));
        for(int i = 0; i < res.length; i++) {
            System.out.println(res[i].getPath().getName());
        }
    }

    public static void deleDir() throws IOException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/test/hadoop/data/testDir");
        fs.delete(path);
        fs.close();
    }

    /**
     * 创建文件夹
     * @throws IOException
     */
    public static void createDir() throws IOException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/test/hadoop/data/testDir");
        //fs.create(path); //是创建普通文件
        fs.mkdirs(path);
        fs.close();
    }

    /**
     *
     * @throws IOException
     */
    public static void writeFile() throws IOException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/test/hadoop/data/testWrite.txt");
        FSDataOutputStream out = fs.create(path);
        //out.writeUTF("da jia hao,cai shi zhen de hao!");
        out.write("da jia hao,cai shi zhen de hao!\n".getBytes());
        out.write("大家好才是真的好!".getBytes());
        fs.close();
    }

    public static void readFile() throws IOException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/test/hadoop/data/testWrite.txt");

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

    public static void readFileV2(String inputPath) throws IOException {
        Configuration conf = initConf();
        FileSystem fs = FileSystem.get(conf);
        InputStream in = null;
        try {
            in = fs.open( new Path(inputPath));
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
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

        DistributedFileSystem dfs = (DistributedFileSystem)fs;
        DatanodeInfo[] dataNodeStats = dfs.getDataNodeStats();

        for(int i=0;i<dataNodeStats.length;i++){
            System.out.println("DataNode_" + i + "_Node:" + dataNodeStats[i].getHostName());
        }
    }
}
