package com.nothing.thread;

import java.util.concurrent.ConcurrentHashMap;

public class CacheTest {

	//缓存系统用ConcurrentHashMap是非常好的选择
	private static ConcurrentHashMap<String , Object> cache = new ConcurrentHashMap<String, Object>();
	
	public Object get(String key){
		Object value = cache.get(key);
		if(cache.get(key)==null){
			value = "xxx";//此处去查找数据库
			cache.put(key, value);
		}
		return value;
	}
}
