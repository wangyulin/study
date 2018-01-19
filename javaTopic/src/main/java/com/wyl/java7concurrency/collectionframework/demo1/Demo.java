package com.wyl.java7concurrency.collectionframework.demo1;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by wangyulin on 30/08/2017.
 */
public class Demo {

    public static void main(String[] args) {
        InputStream is;
        BufferedInputStream bs;
        ConcurrentLinkedDeque concurrentLinkedDeque;
        LinkedBlockingDeque linkedBlockingDeque;
        DelayQueue delayQueue;
        Delayed delayed;
        //LinkedList<String> linkedList;
        Deque deque;
        Map map;
        LinkedList linkedList;

        List<Integer> list = new ArrayList<>();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(3));
        list.add(new Integer(4));
        list.add(new Integer(5));

        Iterator it = list.iterator();

        while(it.hasNext()) {
            it.next();
            it.remove();
        }

        /*for(Integer it : list) {
            list.remove(it);
        }*/

        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }

        System.out.printf(" %d \n", list.size());

        /*while(it.hasNext()) {
            int a = (Integer)it.next();
            list.remove(Integer.valueOf(a));
            if(it.hasNext()){
                int b = (Integer)it.next();
                list.remove(Integer.valueOf(b));
                list.add(a+b);
                it = null;
                System.gc();
                it = list.iterator();
            }
        }*/

        /*while(it.hasNext()){
            int a = (Integer)it.next();
            if(it.hasNext()){
                Integer b = (Integer)it.next();

                list.remove(Integer.valueOf(a));
                list.remove(Integer.valueOf(b));
                list.add(a+b);
                it = null;
                System.gc();
                it = list.iterator();
            }
        }*/

    }

}
