package com.wyl.zookeeper.demo;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.ZooKeeper; 

public class ZookepperJavaDemo implements Watcher{

	private static final int SESSION_TIMEOUT = 1000;
	private static final String CONNECTION_STRING = "wangyulin-test-host:2181";
	private static final String ZK_PATH = "/zk_test_2";
	private static ZooKeeper zk = null;
	
	private CountDownLatch connectedSemaphore = new CountDownLatch(1); 
	
	public static void main(String[] args) {
		ZookepperJavaDemo demo = new ZookepperJavaDemo();
		demo.createConnection(CONNECTION_STRING, SESSION_TIMEOUT);
		System.out.println(zk.getSessionId());
		System.out.println(demo.createPath(ZK_PATH, "Java_API_DATA"));
	}
	
	/** 
     * 关闭ZK连接 
     */ 
    public void releaseConnection() { 
        if (this.zk != null && !this.zk.getState().equals(States.NOT_CONNECTED)) { 
            try { 
                this.zk.close(); 
            } catch ( InterruptedException e ) { 
                // ignore 
                e.printStackTrace(); 
            } 
        } 
    }
	
	public void createConnection(String connectionString,int sessionTimeout) {
		//TODO release old connection
		this.releaseConnection();
		try {
			zk = new ZooKeeper(connectionString,sessionTimeout,this);
			connectedSemaphore.await();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
	}
	
	public boolean createPath(String path, String data) {
		try {
			String result = this.zk.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println(result);
		} catch (KeeperException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void process(WatchedEvent arg0) {
		System.out.println( "收到事件通知：" + arg0.getState() +"\n"  ); 
        if ( KeeperState.SyncConnected == arg0.getState() ) { 
            connectedSemaphore.countDown(); 
        } 
	}
}
