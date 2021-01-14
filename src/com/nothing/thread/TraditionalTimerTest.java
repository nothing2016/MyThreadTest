package com.nothing.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimerTest {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		MyTimerTask myTimerTask = new MyTimerTask();

		new Timer().schedule(myTimerTask, 3000);

		while (true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

class MyTimerTask extends TimerTask {

	@Override
	public void run() {
		System.out.println("mian bombing.......");
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("sub bombing---------");
				new Timer().schedule(new MyTimerTask(), 3000);

			}
		}, 2000);

	}
}