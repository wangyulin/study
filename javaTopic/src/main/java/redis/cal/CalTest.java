package com.wyl.utils.redis.cal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalTest {

	public static void main(String[] args) throws ParseException {
		String date = "2016-11-01";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d = format.parse(date);
		System.out.println(d);
		Date now = new Date();
		
		if(d.after(now)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

}
