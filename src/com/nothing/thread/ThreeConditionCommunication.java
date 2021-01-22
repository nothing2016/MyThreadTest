package com.nothing.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用3个condition来进行三个线程的通信
 */
public class ThreeConditionCommunication {

	public static void main(String[] args) {

		final Business business = new Business();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub2(i);
				}
			}
		}).start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					business.sub3(i);
				}
			}
		}).start();

		for (int i = 1; i <= 50; i++) {
			business.main(i);
		}
	}

	static class Business {

		private Lock lock = new ReentrantLock();
		private Condition condition1 = lock.newCondition();
		private Condition condition2 = lock.newCondition();
		private Condition condition3 = lock.newCondition();
		private int should = 1;

		public void sub2(int i) {
			lock.lock();
			try {
				while (should != 2) {
					try {
						condition2.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 5; j++) {
					System.out.println("sub2 thread sequence of " + j
							+ ", loop of " + i);
				}
				should = 3;
				condition3.signal();
			} finally {
				lock.unlock();
			}
		}

		public void sub3(int i) {
			lock.lock();
			try {
				while (should != 3) {
					try {
						condition3.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 5; j++) {
					System.out.println("sub3 thread sequence of " + j
							+ ", loop of " + i);
				}
				should = 1;
				condition1.signal();
			} finally {
				lock.unlock();
			}
		}

		
		public void main(int i) {
			lock.lock();
			try {
				while (should != 1) {
					try {
						condition1.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("main thread sequence of " + j
							+ ", loop of " + i);
				}
				should = 2;
				condition2.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}
