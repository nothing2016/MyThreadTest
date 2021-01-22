package com.nothing.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition的通信
 */
public class ConditinCommunication {

	public static void main(String[] args) {

		final Business business = new Business();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub(i);
				}
			}
		}).start();

		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}
	}

	static class Business {
		private boolean bshouldsub = true;
		private Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();

		public void sub(int i) {
			lock.lock();
			try {
				while (!bshouldsub) {
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 5; j++) {
					System.out.println("sub thread sequence of " + j
							+ ", loop of " + i);
				}
				bshouldsub = false;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}

		public void main(int i) {
			lock.lock();
			try {
				while (bshouldsub) {
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("main thread sequence of " + j
							+ ", loop of " + i);
				}
				bshouldsub = true;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}
