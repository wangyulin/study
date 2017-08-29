package com.wyl.dp.singleton;

public class SingletonIoDH {

	private SingletonIoDH() {
	}

	private static class HolderClass {
		private final static SingletonIoDH instance = new SingletonIoDH();
	}

	public static SingletonIoDH getInstance() {
		return HolderClass.instance;
	}

	public static void main(String[] args) {
		SingletonIoDH s1, s2;
		s1 = SingletonIoDH.getInstance();
		s2 = SingletonIoDH.getInstance();
		System.out.println(s1 == s2);
	}
}
