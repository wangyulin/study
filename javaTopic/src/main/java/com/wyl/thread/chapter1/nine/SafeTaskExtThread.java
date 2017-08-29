package com.wyl.thread.chapter1.nine;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SafeTaskExtThread extends Thread{
	
	private Date startDate;
	
	public void run() {
		startDate=new Date();
		System.out.printf("Starting Thread: %s : %s\n",Thread.currentThread().getId(),startDate);
		try {
			TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Thread Finished: %s : %s\n",Thread.currentThread().getId(),startDate);
	}
}
