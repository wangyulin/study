package com.wyl.thread.chapter1;

public class PrimeGenerator extends Thread{
	
	public void run(){
		long number = 1L;
		while(true) {
			if (isPrime(number)) {
				System.out.printf("Number %d is Prime\n",number);
			}

			// When is interrupted, write a message and ends
			if (isInterrupted()) {
				System.out.printf("The Prime Generator has been Interrupted\n");
				return;
			}
			number++;
		}
	}
	
	/**
	 *  Method that calculate if a number is prime or not
	 * @param number : The number
	 * @return A boolean value. True if the number is prime, false if not.
	 */
	private boolean isPrime(long number) {
		if (number <=2) {
			return true;
		}
		for (long i=2; i<number; i++){
			if ((number % i)==0) {
				return false;
			}
		}
		return true;
	} 
	
}
