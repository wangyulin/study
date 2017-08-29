package com.wyl.senior;

import java.util.ArrayList;
import java.util.List;

public class A {

	public static void main(String[] args) {
		//B b = new B();
		A a = new A();
		a.test();
	}
	
	public void test() {
		List<String> list = new ArrayList<String>();
		int i = 1;
		while(true) {
			//test();
			new String(i++ +"");
		}
	}

}

class B{
	
	private int n;
	public B() {
		
		System.out.println("Hello B");
		n = 10;
	}
	
	
	
}
