package com.wyl.spring.boot.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by wangyulin on 09/05/2017.
 */
public class DataUtils {

    public static final String INT_SEG = "INT";
    public static final String STRING_SEG = "STRING";
    public static final char COMMA_SEPARATOR = ',';
    public static final char SINGLE_QUOTATION_MARK = '\'';

    public static List<String> patternMatching(String str) {
        Stack<String> sk = new Stack<>();
        List<String> result = new ArrayList<>();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            char item =  str.charAt(i);

            if(i == 0) {
                if(item == SINGLE_QUOTATION_MARK) {
                    sk.push(STRING_SEG);
                    sb.append(item);
                    continue;
                } else {
                    sk.push(INT_SEG);
                    sb.append(item);
                    continue;
                }
            }

            if(item != COMMA_SEPARATOR && item != SINGLE_QUOTATION_MARK) {
                if(str.charAt(i - 1) == COMMA_SEPARATOR && sk.empty()) {
                    sk.push(INT_SEG);
                    sb.append(item);
                    if((i == (str.length() - 1)) && sk.peek().equals(INT_SEG)) {
                        sk.pop();
                        result.add(sb.toString());
                    }
                    continue;
                } else {
                    sb.append(item);
                    continue;
                }
            } else if(item == COMMA_SEPARATOR) {
                if(sk.peek().equals(INT_SEG)){
                    sk.pop();
                    result.add(sb.toString());
                    sb = new StringBuffer();
                    continue;
                } else if((str.charAt(i - 1) == SINGLE_QUOTATION_MARK) && sk.peek().equals(STRING_SEG)) {
                    sk.pop();
                    String res = sb.toString().replaceAll("^\'", "").replaceAll("\'$", "");
                    result.add(res);
                    sb = new StringBuffer();
                    continue;
                }
            }

            if(item == SINGLE_QUOTATION_MARK) {
                if(str.charAt(i - 1) == COMMA_SEPARATOR && sk.empty()) {
                    sk.push(STRING_SEG);
                    sb.append(item);
                    continue;
                } else if((i == str.length() - 1) && sk.peek().equals(STRING_SEG)) {
                    sb.append(item);
                    result.add(sb.toString());
                    sb = new StringBuffer();
                    continue;
                }
            }

            sb.append(item);
        }
        return result;
    }

}
