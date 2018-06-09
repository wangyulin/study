package com.wyl.base;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Created by wangyulin on 15/06/2017.
 */
public class TreeMapDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Map<String, String> map = new TreeMap<>();//new LinkedHashMap<>(16,0.75f,true);//new TreeMap<>();
        //map.put(null, "");
        map.put("xyz", "123");
        map.put("abc", "111");
        map.put("add","100");

        //map.put("xya", "113");

        map.get("xyz");
        /*
        Field tail = map.getClass().getDeclaredField("tail");
        tail.setAccessible(true);
        System.out.println(tail.get(map));

        Entry<String, String> one = getTailByReflection(map);
        System.out.println(one);
        */

        /*for(String key : map.keySet()) {
            System.out.println(key + " - " + map.get(key));
        }*/

        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String name = iterator.next();
            System.out.println(name);
        }
    }

    /*@SuppressWarnings("unchecked")
    public static <K, V> Entry<K, V> getTailByReflection(LinkedHashMap<K, V> map)
            throws NoSuchFieldException, IllegalAccessException {
        Field tail = map.getClass().getDeclaredField("tail");
        tail.setAccessible(true);
        return (Entry<K, V>) tail.get(map);
    }*/

}
