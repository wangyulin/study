package com.wyl.basic.app;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCacheV2 {

    private final int size;
    private LinkedList<String> oldQ;
    private LinkedHashMap<String, String> map;

    public LRUCacheV2(int cap) {
        this.size = cap;
        map = new LinkedHashMap<>();
        oldQ = new LinkedList<>();
    }

    public void put(String k, String v) {
        if (map.containsKey(k)) {
            oldQ.remove(k);
            oldQ.offer(k);
            map.put(k, v);
            return;
        }

        if (map.size() >= size) {
            trim();
        }

        map.put(k, v);
        oldQ.offer(k);
    }

    private void trim() {
        String kTag = oldQ.peek();
        map.remove(kTag);
    }

    public String get(String k) {
        String v = map.get(k);
        if (Objects.nonNull(v)) {
            oldQ.remove(k);
            oldQ.offer(k);
            return v;
        }
        return null;
    }

    public int size() {
        return map.size();
    }
}
