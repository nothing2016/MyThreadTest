package com.nothing.thread;

public class TraditionalThread {
	public static int count = 0;
	public static boolean flag = false;

	public static void main(String[] args) {
		Thread thread = new Thread() {
			@Override
			public void run() {

				while (!Thread.currentThread().isInterrupted()) {
					count++;
					try {
						if (count == 5)
							Thread.currentThread().interrupt();
						System.out.println(Thread.currentThread()
								.isInterrupted());
						if (Thread.currentThread().isInterrupted())
							return;
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						flag = true;
					}
					System.out.println(Thread.currentThread().isInterrupted()
							+ " hello world" + Thread.currentThread().getName()
							+ "------>" + count);
				}
			}
		};
		thread.start();

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println(Thread.currentThread().getName() );
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread2.start();
	}

}
