package com.wyl.zookeeper.demo1;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

public class Client {
	private String pnode = "zk_test_1";
	private String host = "wangyulin-test-host:2181";
	private ZooKeeper zk;
	private List<String> serverList = new ArrayList<String>();

	public void connect() throws Exception {
		zk = new ZooKeeper(host, 5000, new Watcher() {
			public void process(WatchedEvent event) {
				if (event.getType() == EventType.NodeChildrenChanged && ("/" + pnode).equals(event.getPath())) {
					try {
						updateServerList();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		updateServerList();
	}

	public void updateServerList() throws Exception {
		List<String> subNodeList = zk.getChildren("/" + pnode, true);
		serverList = subNodeList;
		System.out.println("server list updated: " + serverList);
	}

	public void handle() throws InterruptedException {
		Thread.sleep(Long.MAX_VALUE);
	}

	public static void main(String[] args) throws Exception {
		Client ac = new Client();
		ac.connect();
		ac.handle();
	}
}