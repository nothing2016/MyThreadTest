package com.mashibing.thread;

import java.util.Date;

public class Thread2 {
	private static int count = 0;

	public static void main(String[] args) {
		Runner runner1 = new Runner();
		runner1.start();
//		runner1.interrupt();
//		for (int i = 0; i < 1000; i++) {
//			System.out.println("我是主线程");
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			if (i == 1) {
//				runner1.interrupt();
//			}
//		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		runner1.interrupt();
		runner1.stop();
	}
	

	static class Runner extends Thread {
		@Override
		public void run() {
			while(true){
				System.out.println(new Date());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
//					return;
				}
			}

//			for (int i = 0; i < 1000; i++) {
//				System.out.println("子线程  count=" + count);
//				count++;
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
////					e.printStackTrace();
//					System.out.println("主线程想要让子线程停止！");
//					return;
//				}
//			}
		}
	}
}
