package com.wyl.kafka.order;

import com.wyl.kafka.order.model.DividedInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangyulin on 17/03/2017.
 */
public class Demo2 {

    public static void main(String[] args) {
        List<DividedInfo> dividedInfos = new ArrayList<>();
        DividedInfo d1 = new DividedInfo();
        d1.setOwnerDesignerMiid(1L);
        dividedInfos.add(d1);

        DividedInfo d2 = new DividedInfo();
        d2.setOwnerDesignerMiid(2L);
        dividedInfos.add(d2);

        DividedInfo d3 = new DividedInfo();
        d3.setOwnerDesignerMiid(-1L);
        dividedInfos.add(d3);

        for (DividedInfo dividedInfo : dividedInfos) {
            System.out.println(dividedInfo);
        }

        System.out.println("----");

        for(DividedInfo dividedInfo : dividedInfos) {
            if(dividedInfo.getOwnerDesignerMiid() == -1L) {
                dividedInfo.setOwnerDesignerMiid(3L);
            }
        }

        for (DividedInfo dividedInfo : dividedInfos) {
            System.out.println(dividedInfo);
        }

        int a = 10, b = 11, c = 12;
        int m = 13;
        int flag = (m == a)? 10 : ((m == b) ? 11 : (m == c) ? 12: 0);
        System.out.println(flag);

        /*List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("xyz");
        list.add("opq");
        list.add("aoe");
        list.add("lmn");
        int index = Collections.binarySearch(list, "lmn");
        System.out.println(index);

        List<String> arlst=new ArrayList<String>();

        arlst.add("TP");
        arlst.add("PROVIDES");
        arlst.add("QUALITY");
        arlst.add("TUTORIALS");

        index = Collections.binarySearch(arlst, "QUALITY");
        System.out.println("'QUALITY' is available at index: "+index);*/
    }

}
