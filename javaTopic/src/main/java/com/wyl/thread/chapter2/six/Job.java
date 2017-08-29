package com.wyl.thread.chapter2.six;

/**
 * This class simulates a job that send a document to print
 *
 */
public class Job implements Runnable {

	/**
	 * The queue to send the documents
	 */
	private PrintQueue printQueue;
	private char tag;
	
	/**
	 * Constructor of the class. Initializes the print queue
	 * @param printQueue the print queue to send the documents
	 */
	public Job(PrintQueue printQueue,char tag){
		this.printQueue=printQueue;
		this.tag = tag;
	}
	
	/**
	 * Core method of the Job. Sends the document to the queue
	 */
	@Override
	public void run() {
		System.out.printf("%s: Going to print a \' " + tag + "\' job\n",Thread.currentThread().getName());
		printQueue.printJob(tag);
		System.out.printf("%s: The document has been printed   \' " + tag + "\'\n",Thread.currentThread().getName());		
	}

}
