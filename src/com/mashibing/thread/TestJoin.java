package com.mashibing.thread;

/**
 * join 线程合并，在B线程中调用A.join()，表示A加入了B,必须让A先执行完成，再到B执行
 */
public class TestJoin {
	public static void main(String[] args) {
		final Thread subThread1 = new Thread(new Runner(), "子线程1");
		subThread1.start();

		Thread subThread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					subThread1.join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < 10; i++) {
					System.out.println("子线程2");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		subThread2.start();
		try {
			subThread2.join();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("我是主线程");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

//		try {
//			subThread1.join();
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
	}

}

class Runner extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}

		}
	}

}