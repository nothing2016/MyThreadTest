package com.nothing.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheTest2 {
	// 用HashMap来做缓存系统，但要自己进行同步,允许一个线程对缓存写数据，多个线程同时读
	private static volatile Map<String, Object> cache = new HashMap<String, Object>();

	private static ReadWriteLock lock = new ReentrantReadWriteLock();

	public static Object get(String key) {
		lock.readLock().lock();
		Object value = null;
		try {
			value = cache.get(key);
			if (value == null) {
				lock.readLock().unlock();
				lock.writeLock().lock();
				try {
					if (value == null) {//防止后面等待的线程重复拿去数据库取值
						value = "xxx";// 这里是从数据库取得数据
						System.out.println("第一次从数据库中拿数值");
						cache.put(key, value);
					}
				} finally {
					lock.writeLock().unlock();
				}
				lock.readLock().lock();
			}
		} finally {
			lock.readLock().unlock();
		}
		return value;
	}
	
	public static void main(String[] args) {
		System.out.println(CacheTest2.get("hello"));
		System.out.println(CacheTest2.get("hello"));
		System.out.println(CacheTest2.get("world"));	
		System.out.println(CacheTest2.get("world"));
	}
}
