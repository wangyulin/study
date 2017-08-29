package com.wyl.ds;

import java.util.*;

/**
 * Created by wangyulin on 4/14/16.
 */
public class VenterDemo {

    public static void main(String[] args) {
        List<String> l = new ArrayList<String>();
        List ll = new LinkedList<String>();
        HashMap p;

        l.add("A");
        l.add("B");
        Vector<String> v = new Vector<String>(l);
        System.out.println(v.get(1));
    }

}
