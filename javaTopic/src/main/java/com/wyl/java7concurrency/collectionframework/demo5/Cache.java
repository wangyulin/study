package com.wyl.java7concurrency.collectionframework.demo5;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class Cache<K, V> {

    public ConcurrentHashMap<K, V> map = new ConcurrentHashMap<K, V>();
    public DelayQueue<DelayedItem<K>> queue = new DelayQueue<DelayedItem<K>>();

    public void put(K k,V v,long liveTime){
        V v2 = map.put(k, v);
        DelayedItem<K> tmpItem = new DelayedItem<K>(k, liveTime);
        if (v2 != null) {
            queue.remove(tmpItem);
        }
        queue.put(tmpItem);
    }

    public Cache(){
        Thread t = new Thread(){
            @Override
            public void run(){
                dameonCheckOverdueKey();
            }
        };
        t.setDaemon(true);
        t.start();
    }

    public void dameonCheckOverdueKey() {
        while (true) {
            DelayedItem<K> delayedItem = queue.poll();
            if (delayedItem != null) {
                map.remove(delayedItem.getT());
                System.out.println(System.nanoTime() + " remove " + delayedItem.getT() + " from cache");
            }
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }

}
