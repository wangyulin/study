package com.wyl.common.utils.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangyulin on 02/02/2018.
 */
public class ForList {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            int i = iter.next();
            if (i == 3) {
                //list.remove(i);
                iter.remove();
            }
        }

        /*List<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        for(int i = 0 ; i < linkedList.size(); i++ ) {
            System.out.println(linkedList.get(i));
        }*/
        List<String> linkedList = new LinkedList<>();

        for(long i=0; i<10000; i++){
            linkedList.add(""+i);
        }

        long ayyaybefore = System.currentTimeMillis();
        ayyaybefore = System.currentTimeMillis();
        for(String ii : linkedList){
        }
        long ayyayafter = System.currentTimeMillis();
        System.out.println("linkedlist使用foreach遍历的时间是:"+(ayyayafter-ayyaybefore)+"ms");

        Iterator literator = linkedList.iterator();
        ayyaybefore = System.currentTimeMillis();
        while (literator.hasNext()){
            literator.next();
        }
        ayyayafter = System.currentTimeMillis();
        System.out.println("linkedList使用iterator遍历的时间是:"+(ayyayafter-ayyaybefore)+"ms");

        ayyaybefore = System.currentTimeMillis();
        for(int ii=0; ii<linkedList.size(); ii++){
            linkedList.get(ii);
        }
        ayyayafter = System.currentTimeMillis();
        System.out.println("linkedlist使用for遍历的时间是:"+(ayyayafter-ayyaybefore)+"ms");

    }

}
