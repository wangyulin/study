package com.wyl.zookeeper.demo1;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class Client2 {
	private String pnode = "zk_test_1";
	private String conf = "conf";
	private String host = "wangyulin-test-host:2181,wangyulin-test-host:2182,wangyulin-test-host:2183";
	private ZooKeeper zk;
	private List<String> serverList = new ArrayList<String>();

	public void connectForList() throws Exception {
		zk = new ZooKeeper(host, 5000, new Watcher() {
			public void process(WatchedEvent event) { // 监视子节点变化
				if (event.getType() == EventType.NodeChildrenChanged && ("/" + pnode).equals(event.getPath())) {
					try {
						updateServerList();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		if (zk.exists("/" + pnode, false) == null) // 如果节点不存在则创建
			zk.create("/" + pnode, "server list".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		updateServerList();
	}

	public void updateServerList() throws Exception {
		List<String> subNodeList = zk.getChildren("/" + pnode, true);
		serverList = subNodeList;
		System.out.println("ListMonitor ===>" + serverList);
	}

	public void handle() throws InterruptedException {
		Thread.sleep(Long.MAX_VALUE); // 不做处理
	}

	public void connectForConf() throws Exception {
		zk = new ZooKeeper(host, 5000, new Watcher() {
			public void process(WatchedEvent event) { // 监视节点数据变化
				if (event.getType() == EventType.NodeDataChanged && conf.equals(event.getPath().split("/")[1])) {
					try {
						updateConf(event.getPath());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		if (zk.exists("/" + conf, false) == null) // 如果节点不存在则创建
			zk.create("/" + conf, "server list".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		updateConf("");
	}

	public void updateConf(String node) throws Exception {
		List<String> subNodeList = zk.getChildren("/" + conf, true);
		for (String subnode : subNodeList) {
			byte[] data = zk.getData("/" + conf + "/" + subnode, true, null);
			// List<String> dataList=new ArrayList<String>();
			// dataList.add(new String(data));
		}
		if (!node.isEmpty())
			System.out.println("DataMonitor ===>[" + node + "] -----> " + new String(zk.getData(node, false, null)));
	}

	public static void main(String[] args) throws Exception {
		ListMonitor listMonitor = new ListMonitor();
		ConfMonitor confMonitor = new ConfMonitor();
		//new Thread(listMonitor, "listmonitor").start();
		new Thread(confMonitor, "confmonitor").start();
	}
}

class ListMonitor implements Runnable {
	public void run() {
		Client2 ac = new Client2();
		try {
			ac.connectForList();
			ac.handle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ConfMonitor implements Runnable {
	public void run() {
		Client2 ac = new Client2();
		try {
			ac.connectForConf();
			ac.handle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
