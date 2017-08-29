package com.wyl.thread.chapter1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimeDemo {

	public static void main(String[] args) throws InterruptedException {
		AtomicInteger inc = null;
		//inc.getAndIncrement();
		// Launch the prime numbers generator
		Thread task = new PrimeGenerator();
		task.start();

		// Wait 5 seconds
		//try {
			TimeUnit.SECONDS.sleep(1);
		/*} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

		// Interrupt the prime number generator
		task.interrupt();
	}

}
