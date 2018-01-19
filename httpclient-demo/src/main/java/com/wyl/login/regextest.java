package com.wyl.login;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangyulin on 04/12/2017.
 */
public class regextest {

    public static void main(String[] args) {
        String str = "http://127.0.1:8082";
        String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        String regex1 = "http://[^\\s'\"]+";
        String regex2 = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        Pattern pattern = Pattern.compile(regex1);
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
        System.out.println(UUID.randomUUID().toString());
    }

}
