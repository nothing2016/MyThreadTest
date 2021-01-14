package com.mashibing.thread;

public class DeadThread extends Thread {
	private static Object object1 = new Object();
	//如果不是static的话，那么得到的两个线程得到的两个对象不是同一对象
	private static Object object2 = new Object();
	private int flag = 1;

	DeadThread(int flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		System.out.println("flag=" + flag);
		if (flag == 1) {
			synchronized (object1) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (object2) {
					System.out.println("flag==1 finish");
				}
			}
		}
		if (flag == 2) {
			synchronized (object2) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (object1) {
					System.out.println("flag== 2 finish");
				}
			}
		}
	}

	public static void main(String[] args) {
		new DeadThread(2).start();
		new DeadThread(1).start();
	}
}
