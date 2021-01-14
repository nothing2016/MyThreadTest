package com.nothing.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture {

	public static void main(String[] args) {
//		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		Future<String> future = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "hello";
			}
		});
		Future<String> future2 = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "world";
			}
		});
		Future<String> future3 = threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "hai";
			}
		});

		System.out.println("异步调用中·········");
		try {
			System.out.println("得到的结果：" + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("异步调用中·········");
		try {
			System.out.println("得到的结果：" + future2.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("异步调用中·········");
		try {
			System.out.println("得到的结果：" + future3.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		threadPool.shutdown();

		// // CompletionService的测试
		// ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
		// CompletionService<Integer> completionService = new
		// ExecutorCompletionService<Integer>(
		// threadPool2);
		// for (int i = 1; i <= 10; i++) {
		// final int task = i;
		// completionService.submit(new Callable<Integer>() {
		// @Override
		// public Integer call() throws Exception {
		// Thread.sleep(new Random().nextInt(5000));
		// return task;
		// }
		// });
		// }
		//
		// System.out.println("完成的结果为：");
		// for (int i = 1; i <= 10; i++) {
		// try {
		// System.out.println(completionService.take().get());
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// } catch (ExecutionException e) {
		// e.printStackTrace();
		// }
		// }
		//
		// threadPool2.shutdown();
	}
}
