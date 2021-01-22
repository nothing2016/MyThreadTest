package com.mashibing.thread;

/**
 * 高风亮节的让出cpu的执行时间，但下一秒又同时去抢占cpu
 */
public class TestYield {
	public static void main(String[] args) {
		MyThread t1 = new  MyThread("t1");
		MyThread t2 = new MyThread("t2");
		t1.start();
		t2.start();
	}
}

class MyThread extends Thread{
	
	MyThread(String s){
		super(s);
	}
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+"-------->"+i);
			if(i%10==0){
				yield();
			}
		}
	}
}