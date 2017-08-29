package com.wyl.thread.chapter2.three;

public class Demo {

	public static void main(String[] args) {
		// Creates an event storage
		Store storage = new Store();

		// Creates a Producer and a Thread to run it
		Producer producer = new Producer(storage);
		Thread thread1 = new Thread(producer);

		// Creates a Consumer and a Thread to run it
		Consumer consumer = new Consumer(storage);
		Thread thread2 = new Thread(consumer);

		// Starts the thread
		thread1.start();
		thread2.start();
	}

}
