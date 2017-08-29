package com.wyl.thread.chapter1.nine;

import java.util.concurrent.TimeUnit;

public class SafeTaskExtThreadMain {

	public static void main(String[] args) {
		for(int i = 0 ; i < 3 ; i++ ) {
			Thread thread = new SafeTaskExtThread();
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
