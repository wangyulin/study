package com.wyl.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo2 {
	public static void main(String[] args) {
		DepotV2 mDepot = new DepotV2(100);
		ProducerV2 mPro = new ProducerV2(mDepot);
		CustomerV2 mCus = new CustomerV2(mDepot);
		
		mPro.produce(60);
	    mPro.produce(120);
	    mCus.consume(90);
	    mCus.consume(150);
	    mPro.produce(110);
	}
}

class CustomerV2 {
    private DepotV2 depot;
    
    public CustomerV2(DepotV2 depot) {
        this.depot = depot;
    }

    // 消费产品：新建一个线程从仓库中消费产品。
    public void consume(final int val) {
        new Thread("Consume ") {
            public void run() {
                depot.consume(val);
            }
        }.start();
    }
}

class ProducerV2 {
    private DepotV2 depot;
    
    public ProducerV2(DepotV2 depot) {
        this.depot = depot;
    }

    // 消费产品：新建一个线程向仓库中生产产品。
    public void produce(final int val) {
        new Thread("Producer") {
            public void run() {
                depot.produce(val);
            }
        }.start();
    }
}

class DepotV2 { 
	private int capacity;    // 仓库的容量
    private int size;        // 仓库的实际数量
    private Lock lock;       // 独占锁
    private Condition fullCondtion;     // 生产条件
    private Condition emptyCondtion;    // 消费条件

    public DepotV2(int capacity) {
    	this.capacity = capacity;
        this.size = 0;
        this.lock = new ReentrantLock();
        this.fullCondtion = lock.newCondition();
        this.emptyCondtion = lock.newCondition();
    }

    public void produce(int val) {
        lock.lock();
        try {
        	int left = val;
        	while(left > 0) {
        		while(size >= capacity) 
        			fullCondtion.await();
    			int inc = (size+left)>capacity ? (capacity-size) : left;
    			size += inc;
    			left -= inc;
    			System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",Thread.currentThread().getName(), val, left, inc, size);
    			emptyCondtion.signalAll();
        	}
        } catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
            lock.unlock();
        }
    }

    public void consume(int val) {
        lock.lock();
        try {
        	int left = val;
        	while (left > 0) {
        		while (size <= 0)
        			emptyCondtion.await();
        		int dec = (size<left) ? size : left;
        		size -= dec;
        		left -= dec;
        		System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n", Thread.currentThread().getName(), val, left, dec, size);
        		fullCondtion.signalAll();
        	}
        } catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
            lock.unlock();
        }
    }
    
    public String toString() {
    	 return "capacity:"+capacity+", actual size:"+size;
    }
}
