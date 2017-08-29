package com.wyl.thread.chapter2.three;

public class Consumer implements Runnable{

	/**
	 * Store to work with
	 */
	private Store storage;
	
	/**
	 * Constructor of the class. Initialize the storage
	 * @param storage The store to work with
	 */
	public Consumer(Store storage){
		this.storage=storage;
	}
	
	/**
	 * Core method for the consumer. Consume 100 events
	 */
	@Override
	public void run() {
		for (int i=0; i<10; i++){
			storage.get();
		}
	}

}
