package com.wyl.dp.singleton;

public class SingletonStaticDemo {
	
	public static final SingletonStaticDemo singleton;
	
	static {
		singleton = new SingletonStaticDemo();
	}
	
	private SingletonStaticDemo(){}
	
	public void exec() {
		System.out.println("Hello World !");
	}
	
	public static void main(String[] args) {
		singleton.exec();
		System.out.println(singleton);
	}

}
