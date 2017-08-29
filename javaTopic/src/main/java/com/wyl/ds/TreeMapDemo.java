package com.wyl.ds;

import java.util.TreeMap;

/**
*
* Created by wangyulin on 4/13/16.
*/
public class TreeMapDemo {

	public static void main(String[] args) {
		TreeMap<String,String> map = new TreeMap<String,String>();
		//map.put(null,"");
		map.put("A", "12345");
		map.put("B", "432");
		map.put("C", "987");
		map.put("E", "333");
		map.put("F", "222");
		map.get("");
		map.remove("B");
		//System.out.println(map.ceilingEntry("D"));
		String z = null;
		System.out.println(z.compareTo(null));

	}

}
