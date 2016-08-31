package com.wyl.akka;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println(0.01d + 0.05d);
		System.out.println(new BigDecimal(Double.toString(0.01)).add(new BigDecimal(Double.toString(0.05))) );
		byte[] buf = {0x01, 0x01, 0x02, 0x71};
		
		System.out.println(1<<3);
	}
}
