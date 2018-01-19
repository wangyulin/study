package com.wyl.common.utils.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyulin on 07/01/2018.
 */
public class RegexDemo {

    public static void main(String[] args){
        Pattern p = Pattern.compile("^.*d1.*$");
        Matcher m = p.matcher("ad1min");
        if(m.matches()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
