package com.wyl.thread.chapter2.three;

/**
 * This class implements a producer of events.
 *
 */
public class Producer implements Runnable {

	/**
	 * Store to work with
	 */
	private Store storage;
	
	/**
	 * Constructor of the class. Initialize the storage.
	 * @param storage The store to work with
	 */
	public Producer(Store storage){
		this.storage=storage;
	}
	
	/**
	 * Core method of the producer. Generates 100 events.
	 */
	@Override
	public void run() {
		for (int i=0; i<10; i++){
			storage.put();
		}
	}
}
