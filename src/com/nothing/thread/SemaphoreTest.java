package com.nothing.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static void main(String[] args) {
		final Semaphore semaphore = new Semaphore(3);
		ExecutorService threadPool = Executors.newCachedThreadPool();
		for(int i=0; i<10;i++){
			Runnable runner = new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+"请求信号灯");
					try {
						semaphore.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"得到了灯，当前有"+(3-semaphore.availablePermits())+"个线程同时运行");
					System.out.println(Thread.currentThread().getName()+"准备释放信号灯");
					semaphore.release();
					System.out.println(Thread.currentThread().getName()+"释放了灯，当前有"+(3-semaphore.availablePermits())+"个线程同时运行");
					
				}
			};
			threadPool.execute(runner);
		}
		threadPool.shutdown();
	}
}
