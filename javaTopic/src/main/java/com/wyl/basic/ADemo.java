package com.wyl.basic;

public class ADemo {
	public static void main(String[] args) throws InterruptedException {
		A a1 = new A();
		new Thread(new T1(a1)).start();
		A a2 = new A();
		new Thread(new T2(a2)).start();
		int count = 0;
		while(count < 40) {
			Thread.sleep(500);
			System.out.print(".");
			count ++;
		}
	}
	
	
	private static class T1 implements Runnable {
		
		private A a;
		
		public T1(A a) {
			this.a = a;
		}

		@Override
		public void run() {
			a.x();
		}
	}
	
	private static class T2 implements Runnable {
		
		private A a;
		
		public T2(A a) {
			this.a = a;
		}

		@Override
		public void run() {
			a.y();
		}
	}
}

class A {
	
	public void x() {
		synchronized(A.class) {
			try {
				System.out.println("X method is running ...");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("\rX method is over");
		}
	}
	
	public void y() {
		synchronized(A.class) {
			try {
				System.out.println("Y method is running ...");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("\rY method is over");
		}
	}	
}