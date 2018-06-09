package com.wyl.ds;

import java.util.Hashtable;
import java.util.WeakHashMap;

public class HashTableDemo {

	public static void main(String[] args) {
		Hashtable<String, String> ht = new Hashtable<>();
		//ht.put("", "");
		ht.put("A", "A1");
		System.out.println(ht.get("A"));
		WeakHashMap w;
	}

}
