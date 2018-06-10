package com.wyl.ds;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: wangyulin
 * @Date: 2018/6/9 16:15
 * @Description:
 */
public class ConcurrentHashMapDemo2 {

    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap();

        map.put("abc", Integer.valueOf("123"));
        map.put("bcd", Integer.valueOf("234"));
        map.put("cde", Integer.valueOf("345"));
        map.put("def", Integer.valueOf("456"));


        for(final Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            System.out.println(key + " : " + value);

        }
    }

}
