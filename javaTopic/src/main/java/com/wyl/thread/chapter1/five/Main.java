package com.wyl.thread.chapter1.five;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		// Creates a FileClock runnable object and a Thread
		// to run it
		FileClock clock = new FileClock();
		Thread thread = new Thread(clock);

		// Starts the Thread
		thread.start();
		try {
			// Waits five seconds
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		;
		// Interrupts the Thread
		thread.interrupt();
	}

}
