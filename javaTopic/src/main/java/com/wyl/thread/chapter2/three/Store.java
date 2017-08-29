package com.wyl.thread.chapter2.three;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Store {
	
	private long maxSize;
	
	private List<Date> storage;
	
	public Store() {
		maxSize = 10;
		storage = new LinkedList<Date>();
	}
	
	public synchronized void put() {
		while(storage.size() >= maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Date t = new Date();
		storage.add(t);
		System.out.printf("Put: %d   %s\n",storage.size(),t);
		notifyAll();
	}
	
	public synchronized void get() {
		while(storage.size() <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Date t = ((LinkedList<Date>)storage).poll();
		System.out.printf("Get: %d: %s\n",storage.size(),t);
		notifyAll();
	}
	
}
