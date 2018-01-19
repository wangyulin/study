package com.wyl.utils.redis.demo;

import java.util.Calendar;
import java.util.Date;

public class Task implements Runnable {

	private String name;
	private Calendar cal;
	
	@SuppressWarnings("unused")
	private long counter = 0L;
	
	public Task(String name,Calendar cal) {
		this.name = name;
		this.cal = cal;
	}
	
	/**
	 * Main method of the class
	 */
	@Override
	public void run() {
		String key = CounterUtils.getKey(this.name);
//		if(DemoUtils.isExist(DemoUtils.keyName, key)) {
//			System.out.println("OK");
//		}
		
		while(true) {
			System.out.println(name + " : " + CounterUtils.get(CounterUtils.getKey(name)));
			CounterUtils.setEntity(key);
			CounterUtils.inc(key);
			counter ++;
			if(cal.getTimeInMillis() < new Date().getTime()) {
				System.out.println(name + " : " + counter);
				System.exit(0);
			}
			//CounterUtils.del(CounterUtils.getKey(name));
		}
		
		
	}

}