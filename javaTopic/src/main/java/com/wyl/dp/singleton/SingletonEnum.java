package com.wyl.dp.singleton;

public enum SingletonEnum {
	INSTANCE;
	
	public void exec() {
		System.out.println("Hello World !");
	}
	
	public static void main(String[] args) {
		SingletonEnum sole=SingletonEnum.INSTANCE;
		sole.exec();
		System.out.println(sole.toString());
	}
}