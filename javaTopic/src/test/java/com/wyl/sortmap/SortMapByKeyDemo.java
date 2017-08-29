package com.wyl.sortmap;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wangyulin on 12/27/15.
 */
public class SortMapByKeyDemo {
    public static void main(String[] args){
        Map<String, String> map = new TreeMap<String, String>();
        map.put("A", "a");
        map.put("C", "t");
        map.put("B", "b");
        map.put("F", "c");
        Map<String, String> resultMap = sortMapByKey(map);

        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }
}

class MapKeyComparator implements Comparator<String> {

    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}