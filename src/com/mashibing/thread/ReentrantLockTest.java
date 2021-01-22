package com.mashibing.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 依次让A.B.C线程执行，循环执行10次
 *
 * condition.await() 会让当前的线程挂起，condition.signal()会让当前线程回到运行态继续执行
 */
public class ReentrantLockTest {
	public static void main(String[] args) {
		final Business business = new Business();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					business.sub1(i);
				}
			}
		},"A").start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					business.sub2(i);
				}
			}
		},"B").start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					business.sub3(i);
				}
			}
		},"C").start();

		
	}

	static class Business {

		private Lock lock = new java.util.concurrent.locks.ReentrantLock();
		private Condition condition1 = lock.newCondition();
		private Condition condition2 = lock.newCondition();
		private Condition condition3 = lock.newCondition();
		private int should = 1;

		public void sub2(int i) {
			lock.lock();
			try {
				// 调用await方法时，建议全部使用while而不是if,因为如果时多个线程signal的时候，有可能是一个捣乱的线程执行了condition3.signal();但却没有调用 should = 3
				// 所以需要再判断一次，
				while (should != 2) {
					try {
						condition2.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// for (int j = 1; j <= 5; j++) {
				// System.out.println("sub2 thread sequence of " + j
				// + ", loop of " + i);
				// }
				System.out.println(Thread.currentThread().getName());
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
				// for (int j = 1; j <= 5; j++) {
				// System.out.println("sub3 thread sequence of " + j
				// + ", loop of " + i);
				// }
				System.out.println(Thread.currentThread().getName());
				should = 1;
				System.out.println(Thread.currentThread().getName() + "沉睡了5秒");
				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName() + "马上呼唤condition1");
				condition1.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

		public void sub1(int i) {
			lock.lock();
			try {
				while (should != 1) {
					try {
						condition1.await();
						System.out.println(Thread.currentThread().getName() + "终于苏醒了");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// for (int j = 1; j <= 10; j++) {
				// System.out.println("main thread sequence of " + j
				// + ", loop of " + i);
				// }
				System.out.println(Thread.currentThread().getName());
				should = 2;
				condition2.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}
