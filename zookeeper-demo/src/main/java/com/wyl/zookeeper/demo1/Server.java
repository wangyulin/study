package com.wyl.zookeeper.demo1;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.proto.WatcherEvent;

public class Server {
	private String pnode="zk_test_1";
	private String snode="sub";
	private String host="wangyulin-test-host:2181,wangyulin-test-host:2182,wangyulin-test-host:2183";
	public void connect(String content) throws Exception {
		ZooKeeper zk=new ZooKeeper(host,5000,new Watcher(){
			public void process(WatchedEvent event){
				//no process
			}
		});

		System.out.println("status :" + zk.getState ());
		String createdPath=zk.create("/"+pnode+"/"+snode, content.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println("create :"+createdPath);
		System.out.println("sessionId :" + zk.getSessionId ());

	}
	public void handle() throws InterruptedException{
		Thread.sleep(Long.MAX_VALUE);
	}
	public static void main(String[] args){
		Multi server1=new Multi(1);
		Multi server2=new Multi(2);
		Multi server3=new Multi(3);
		new Thread(server1,"server1").start();
		new Thread(server2,"server1").start();
		new Thread(server3,"server1").start();
	}
}
 class Multi implements Runnable {
	private int seq;
	public Multi(int seq){
		this.seq=seq;
	}
	public void run(){
		try {
			Server as=new Server();
			as.connect("register");
			System.out.println("server"+this.seq+" registered");
			as.handle();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}