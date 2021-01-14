package com.nothing.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTessst {

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
							Thread.sleep(20);
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
//		threadPool2.shutdown();
	}
}
