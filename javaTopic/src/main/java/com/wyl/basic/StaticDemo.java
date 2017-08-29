package com.wyl.basic;

public class StaticDemo {

	public static void main(String[] args) {
		System.out.println(Sub.value);
	}

}

class Supper {
	
	static {
		System.out.println("Supper");
	}
	
	static int value = 1;
	
}

class Sub extends Supper {
	
	static {
		System.out.println("Sub");
	}
	
}
