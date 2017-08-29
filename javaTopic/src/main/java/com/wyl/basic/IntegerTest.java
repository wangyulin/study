package com.wyl.basic;

/**
 * 自动装箱测试
 * 
 * @author wangyulin
 */
public class IntegerTest {
	public static void main(String[] args) {
		Short s1 = 1;
		Short s2 = s1;
		System.out.println(s1 == s2);

		Short s11 = 1;
		Short s22 = s11;
		s11++;
		System.out.println(s11 == s22);
		testWrapper();
		
		NullTest myNullTest = null;
		System.out.println(myNullTest.getInt());
	}

	private static void testWrapper() {
		Short s1 = new Short((short) 1);
		Short s2 = s1;
		short tempS1 = s1.shortValue();
		tempS1++;
		s1 = new Short(tempS1);
		System.out.println(s1 == s2);
	}
}

class NullTest {
	public static int getInt() {
		return 1;
	}
}
