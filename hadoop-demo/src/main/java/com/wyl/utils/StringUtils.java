package com.wyl.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	private static Pattern pattern_1 = Pattern.compile("(?m)^\'(.+)\'$");
	private static Pattern pattern_2 = Pattern.compile("(?m)^\"(.+)\"$");

	public static String trim(String originalStr) {
		
		Matcher matcher_1 = pattern_1.matcher(originalStr);
		Matcher matcher_2 = pattern_2.matcher(originalStr);
		
		String result = originalStr;
		if (matcher_1.find()) {
			result = matcher_1.group(1);
		}
		
		if (matcher_2.find()) {
			result = matcher_2.group(1);
		}
		return result;
	}

	public static void main(String[] args) {
		String s = "\'aasd\"a\'";
		System.out.println(trim(s));
		System.out.println(trim("ABC"));
	}

}
