package com.mashibing.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Test2 {

	private int id;
	private String name;
	public Test2(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
	public static void main(String[] args) {
		Test2 a = new Test2(1, "zhangsan");
		Test2 b = new Test2(1, "zhangsan");
		System.out.println(new Test2(1, "zhangsan"));
		System.out.println(new Test2(1, "zhangsan"));
		Map< Test2, String> map = new HashMap<Test2, String>();
		map.put(a, "a");
		map.put(b, "b");
//		List<String> strs = (Collection<String>)map.values();
		for(String str : map.values()){
			System.out.println(str);
		}
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test2 other = (Test2) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
