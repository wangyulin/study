package com.wyl.sync;

import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//import org.junit.Test;

public class ReentrantLockTest {
	private static ReentrantLock fairLock = new ReentrantLock(true);
	private static ReentrantLock unfairLock = new ReentrantLock();

	//@Test
	public void fair() {
		System.out.println("fair version");
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(new Job(fairLock));
			thread.setName("" + i);
			thread.start();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void unfair() {
		System.out.println("unfair version");
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(new Job(unfairLock));
			thread.setName("" + i);
			thread.start();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static class Job implements Runnable {
		private ReentrantLock lock;

		public Job(ReentrantLock lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				lock.lock();
				try {
					System.out.println("Lock by:" + Thread.currentThread().getName() + " ==> " + lock.getQueueLength());
				} finally {
					lock.unlock();
				}
			}
		}
	}
}
