package com.wyl.kafka.dstributedlock1;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 13/03/2017.
 */
public class DistributeLockDemo {

    public static void main(String[] args) {
        String servers = "wangyulin-test-host:2181";
        CuratorFramework curator = CuratorFrameworkFactory.builder().retryPolicy(new ExponentialBackoffRetry(10000, 3)).connectString(servers).build();
        curator.start();

        final InterProcessMutex lock = new InterProcessMutex(curator, "/global_lock");

        Executor pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++ ) {
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        lock.acquire();
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally{
                        try {
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }

}
