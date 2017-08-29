package com.wyl.basic;

/**
*
* Created by wangyulin on 4/13/16.
*/
public class HashCodeDemo {

	public static void main(String[] args) {
		String s = new String("ABC");
		String c = "ABC";
		System.out.println(s == c);
		System.out.println(s.equals(c));
		System.out.println(s.hashCode());
		System.out.println(c.hashCode());
	}

}
