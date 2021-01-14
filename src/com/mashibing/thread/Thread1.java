package com.mashibing.thread;

public class Thread1 {
	public static void main(String[] args) {
		Runner1 runner1 = new Runner1();
		Thread thread1 = new Thread(runner1);
		// runner1.run();
		// thread1.run();
		thread1.start();

		for (int i = 0; i < 100; i++) {
			System.out.println("主线程");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

class Runner1 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("子线程");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}