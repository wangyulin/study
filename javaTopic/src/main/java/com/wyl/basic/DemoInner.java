package com.wyl.basic;

import com.wyl.basic.Outer.Inner;

class Outer {
	
	private static int age = 1;
	
	static class Inner {
		private int age = 101;

		void test() {
			System.out.println(Outer.age);
			System.out.println(this.age);
		}
	}
}

public class DemoInner{
	public static void main(String[] args) {
		Inner in = new Inner();
		in.test();
	}
}