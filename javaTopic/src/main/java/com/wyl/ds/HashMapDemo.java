package com.wyl.ds;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangyulin on 4/15/16.
 */
public class HashMapDemo {

    public static void main(String[] args) {
    	HashMap<String, String> map = new HashMap<>();
        System.out.println(map.put("语文","1"));
        System.out.println(map.put("语文","1"));
        map.put("数学","2");
        map.put("英语","3");
        map.put("政治","4");
        map.put("生物","5");
        map.put("化学","6");
        map.put("物理","7");
        map.put("体育","8");
        map.put("美术","2");
        map.put(null,null);

        for(Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
