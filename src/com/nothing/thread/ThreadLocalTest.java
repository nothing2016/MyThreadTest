package com.nothing.thread;

import java.util.Random;


public class ThreadLocalTest {

	
	public static void main(String[] args) {
	
		for(int i=0;i<3;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt();
					ThreadScopeData.getThreadInstance().setName("haha"+data);
					new A().get();
					new B().get();
				}
			} ).start();
		}
		
		
	}
	
	
	static class A{
		public void get(){
			String data =ThreadScopeData.getThreadInstance().getName();
			System.out.println("A from " + Thread.currentThread().getName() 
					+ " get data :" + data);
		}
	}
	
	static class B{
		public void get(){
			String data =ThreadScopeData.getThreadInstance().getName();
			System.out.println("B from " + Thread.currentThread().getName() 
					+ " get data :" + data);
		}
	}
}

class ThreadScopeData{
	
	private static ThreadLocal<ThreadScopeData> LOC = new ThreadLocal<ThreadScopeData>();
	
	//得到当前线程的一个实例
	public static ThreadScopeData getThreadInstance(){
		ThreadScopeData instance = LOC.get();
		if(instance==null){
			instance = new ThreadScopeData();
			LOC.set(instance);
		}
		return instance;
	}
	private ThreadScopeData(){
		
	}
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}