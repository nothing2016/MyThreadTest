package com.nothing.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) {
//		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		ExecutorService threadPool = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {
			final int task = i;
			threadPool.execute(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 5; j++) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName()
								+ " is loop of " + j + " in task " + task);
					}
				}
			});

		}
		threadPool.shutdown();

		ScheduledExecutorService threadPool2 = Executors.newScheduledThreadPool(3);
		threadPool2.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("bombing");
			}
		}, 2,2, TimeUnit.SECONDS);
		// 如果下面这个注释打开的话，bombing就不能按时完成了，因为还没有执行完的时候就已经被shutdown关闭掉了
//		threadPool2.shutdown();
	}
}
