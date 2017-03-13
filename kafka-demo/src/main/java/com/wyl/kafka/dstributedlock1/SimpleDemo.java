package com.wyl.kafka.dstributedlock1;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Created by wangyulin on 13/03/2017.
 */
public class SimpleDemo {

    private static final String parent = "/zk_test_1";
    private static final String path = "/zk_test_1";
    private static final String connectString = "wangyulin-test-host:2181";


    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, new ExponentialBackoffRetry(1000, 3));
        client.start();

        //创建一个节点
        //client.create().forPath(path + "/head", new byte[0]);
        /*client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath(path, "hello, zk".getBytes());*/


        // 异步地删除一个节点
        //client.delete().forPath(path);

        client.create().withMode(CreateMode.EPHEMERAL).forPath(path, "hello".getBytes());

        byte[] buf = client.getData().forPath(path);
        System.out.println("get data path:" + path + ", data:" + new String(buf));

        client.setData().inBackground().forPath(path, "ricky".getBytes());
        buf = client.getData().forPath(path);
        System.out.println("get data path:" + path + ", data:" + new String(buf));


        Stat stat = client.checkExists().forPath(path);
        if(stat==null){
            System.out.println("exec create path:" + path);
        }else {
            System.out.println("exec getData");
        }

        Thread.sleep(10000);

        if(null != client) {
            client.close();
        }
    }

}
