package com.wyl.demo1;

import com.wyl.model.User;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

/**
 * Created by wangyulin on 01/07/2017.
 */
public class TestPool {
    final static Logger LOGGER = LoggerFactory.getLogger(TestPool.class);

    public static void main(String[] args) {
        GenericObjectPool<User> pool = new GenericObjectPool<>(new UserFactory());
        AbandonedConfig abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedOnMaintenance(true); //在Maintenance的时候检查是否有泄漏
        abandonedConfig.setRemoveAbandonedOnBorrow(true); //borrow 的时候检查泄漏
        abandonedConfig.setRemoveAbandonedTimeout(10); //如果一个对象borrow之后10秒还没有返还给pool，认为是泄漏的对象
        abandonedConfig.setLogAbandoned(true);
        //abandonedConfig.setUseUsageTracking(true);
        PrintWriter logWriter = new PrintWriter(System.out);
        abandonedConfig.setLogWriter(logWriter);
        pool.setAbandonedConfig(abandonedConfig);
        pool.setTimeBetweenEvictionRunsMillis(5000); //5秒运行一次维护任务

        GenericKeyedObjectPool genericKeyedObjectPool;
        pool.setMaxTotal(100);
        try {
            User user = pool.borrowObject();
            user.setAge(10);
            user.setName("mh");
            System.out.println(user.toString());
            Thread.sleep(11000);
            pool.returnObject(user);

            User u2 = pool.borrowObject();
            System.out.println(u2.getName());
            pool.invalidateObject(u2);

            User u3 = pool.borrowObject();
            System.out.println(u3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
