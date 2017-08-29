package com.wyl.ds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyulin on 4/14/16.
 */
public class ListDemo {

    public static void main(String[] args) {
        ArrayList l = new ArrayList();

        l.add(1);
        l.add(2);
        l.add(3);

        ArrayList c = (ArrayList)l.clone();
        c.remove(1);
        Object [] a = (Object [])l.toArray();

        for(int i = 0; i< l.size(); i++) {
           // System.out.println(l.get(i));
        }

        System.out.println(1 << 4);

    }

}
