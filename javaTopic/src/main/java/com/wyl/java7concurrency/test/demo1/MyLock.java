package com.wyl.java7concurrency.test.demo1;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class MyLock extends ReentrantLock {

    public String getOwnerName() {
        if(this.getOwner() == null) {
            return "None";
        }
        return this.getOwner().getName();
    }

    public Collection<Thread> getThreads() {
        return this.getQueuedThreads();
    }

}
