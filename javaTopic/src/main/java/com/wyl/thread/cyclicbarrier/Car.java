package com.wyl.thread.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {  
	  
    /** 汽车ID */  
    private final String id;  
  
    /** 门闸 */  
    private static CyclicBarrier cyclicBarrier;  
    /** 里程 */  
    private AtomicInteger        strides = new AtomicInteger(0);  
  
    private static Random        rand    = new Random(47);  
  
    public Car(CyclicBarrier barrier, String id) {  
        cyclicBarrier = barrier;  
        this.id = id;  
    }  
  
    public int getStrides() {  
        return strides.get();  
    }  
  
    public void run() {  
        try {  
            while (!Thread.interrupted()) {  
                synchronized (this) {  
                    strides.set(strides.get() + rand.nextInt(3));  
                }  
                // 准备了可以开始，当门闸所有的线程都已经调用了await方法则可以继续执行  
                cyclicBarrier.await();  
            }  
        } catch (InterruptedException e) {  
  
        } catch (BrokenBarrierException be) {  
            throw new RuntimeException(be);  
        }  
    }  
  
    @Override  
    public String toString() {  
        return " [car " + id + "]";  
    }  
  
    public String tracks() {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < getStrides(); i++) {  
            sb.append("-");  
        }  
        sb.append(id);  
        return sb.toString();  
    }  
}
