package com.wyl.java7concurrency.threadmanagement.demo5b.core;

import com.wyl.java7concurrency.threadmanagement.demo5b.event.Event;
import com.wyl.java7concurrency.threadmanagement.demo5b.task.CleanerTask;
import com.wyl.java7concurrency.threadmanagement.demo5b.task.WriterTask;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Main class of the example. Creates three WriterTaks and a CleanerTask 
 *
 */
public class Main {

	/**
	 * Main method of the example. Creates three WriterTasks and a CleanerTask
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Creates the Event data structure
		Deque<Event> deque=new ArrayDeque<Event>();
		
		// Creates the three WriterTask and starts them
		WriterTask writer=new WriterTask(deque);
		for (int i=0; i<3; i++){
			Thread thread=new Thread(writer);
			thread.start();
		}
		
		// Creates a cleaner task and starts them
		CleanerTask cleaner=new CleanerTask(deque);
		cleaner.start();

	}

}
