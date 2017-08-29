package com.wyl.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo {

	public static void main(String[] args) {
		AtomicInteger l = new AtomicInteger(1);
		for(int i = 0; i < 1000 ; i ++) {
			new Thread(new Job(l)).start();
		}
	}
}

class Job implements Runnable {
	
	private AtomicInteger l;
	
	public Job(AtomicInteger l) {
		this.l = l;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10 ; i ++) {
			System.out.println(l.getAndIncrement());
		}
	}
	
}
