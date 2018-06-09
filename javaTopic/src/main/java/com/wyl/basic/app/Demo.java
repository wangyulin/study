package com.wyl.basic.app;

public class Demo {

    public static void main(String[] args) {
        /*LruCache<String, String> cache = new LruCache<>(5);
        cache.put("a", "a");
        cache.put("b", "b");
        cache.put("c", "c");
        cache.put("d", "d");
        cache.put("e", "e");
        cache.put("f", "f");

        System.out.println(cache.size());
        System.out.println(cache.get("a"));*/

        LRUCacheV2 lruCacheV2 = new LRUCacheV2(3);
        lruCacheV2.put("a", "a");
        lruCacheV2.put("b", "b");
        lruCacheV2.put("c", "c");
        lruCacheV2.get("a");
        lruCacheV2.get("b");
        lruCacheV2.put("d", "d");
        lruCacheV2.put("e", "e");
        System.out.println(lruCacheV2.size());
        System.out.println(lruCacheV2.get("a"));
        System.out.println(lruCacheV2.get("b"));
        System.out.println(lruCacheV2.get("c"));
        System.out.println(lruCacheV2.get("d"));
        System.out.println(lruCacheV2.get("e"));
    }

}
