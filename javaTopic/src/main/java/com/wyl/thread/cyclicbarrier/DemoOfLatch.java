package com.wyl.thread.cyclicbarrier;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class DemoOfLatch {

	// 利用闭锁 CountDownLatch 控制主线程和子线程的同步 
	public static void main(String[] args) {
		Callable call;
		int numberOfThread = 5;
		final CountDownLatch startLatch = new CountDownLatch(1);// 用于控制子线程开始
		final CountDownLatch stopLatch  = new CountDownLatch(numberOfThread);// 用于子线程计数 
		final AtomicInteger count = new AtomicInteger(0);// 用于分配子线程唯一标识 
		
		for(int i = 0;i < numberOfThread ; i++) {
			Thread thread = new Thread(new Runnable(){
				@Override
				public void run() {
					int tid = count.getAndIncrement();
					try{
						startLatch.await();// 等代主线程打开启动信号
						System.out.printf("Thread %d start ...%n",tid);
						int duration = (int)(Math.random() * 5000);
						Thread.sleep(duration);
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();
					} finally {
						System.out.printf("Thread %d stoped ... %n",tid);
						stopLatch.countDown();// 线程终止前减少线程计数 
					}
				}
			});
			thread.start();
		}
		
		System.out.println("Main thead do preparation work for child threads ...");
		try {
			Thread.sleep(2000);// 在放行子线程之前做点什么别的事情 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main thread let child threads go..."); 
        startLatch.countDown();// 打开闭锁放行所有子线程 
		
        try { 
            System.out.println("Main thread wait for all child threads..."); 
            stopLatch.await(); // 等待子线程计数降为 0 即所有子线程执行完毕 
        } catch (InterruptedException e) { 
            e.printStackTrace(); 
        } 
		System.out.println("Main thread exit...");
	}
}
