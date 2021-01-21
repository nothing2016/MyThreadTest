package com.mashibing.thread;

/**
 * 100个线程对i++
 * 100个线程对i--
 * 用synchronized对对象进行同步，实现线程安全
 */
public class MultiThread {
	private static final SharedData data = new SharedData();

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					data.inc();
					
				}
			}).start();
		
			new Thread(new Runnable() {

				@Override
				public void run() {
					data.dec();
					
				}
			}).start();
		}
	}
}

class SharedData {
	private int j = 0;

	public synchronized void inc() {
		j++;
		show();
	}

	public synchronized  void dec() {
		j--;
		show();
	}

	public  void show() {
		System.out.println("j------>" + j);
	}
}