package com.wyl.sync;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreDemo {

	// public static Semaphore semaphore = new Semaphore(5);
	// public Semaphore semaphore = new Semaphore(5);

	public int count = 0;

	public static void main(String[] args) {
		ReentrantLock lock;
		ExecutorService exec = Executors.newCachedThreadPool();
		Semaphore semaphore = new Semaphore(5);
		for (int i = 0; i < 20; i++) {
			JobTask job = new SemaphoreDemo().new JobTask(semaphore, "Thread-" + (i+1), i);
			// exec.execute(job);
			exec.submit(job);
		}

		exec.shutdown();
	}

	class JobTask extends Thread {
		private Semaphore semaphore;
		private final String name;
		private final String black_space;

		public JobTask(Semaphore semaphore, String name, int index) {
			this.semaphore = semaphore;
			this.name = name;
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < index; i++) {
				sb.append("------||");
			}
			black_space = sb.toString();
		}

		@Override
		public void run() {
			super.run();
			try {
				semaphore.acquire();
				count += 1;
				Random random = new Random();
				long waiting = random.nextInt(5000);
				Thread.sleep(waiting);
				System.out.println(semaphore.availablePermits() + " : " + black_space + "JobTask " + name + " 开始工作...");
				for (int e = 0; e < 5; e++) {
					Thread.sleep(1000);
					System.out.println(semaphore.availablePermits() + " : " + black_space + "JobTask " + name + " say \"Hello World !\"");
				}
				System.out.println(semaphore.availablePermits() + " : " + black_space + "JobTask " + name + " 结束工作...");

				// Thread.sleep(waiting);
				count -= 1;
				semaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
