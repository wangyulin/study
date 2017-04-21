package com.wyl.common.utils.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyulin on 10/04/2017.
 */
public class Demo1 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for(int j = 11; j <= 91; j++) {
            list.add(j);
        }

        int total = list.size();
        int pageSize = 10;
        int pageNum = (total/10) + ((total % pageSize) == 0 ? 0 : 1);
        int start = 0;
        int last = 0;
        System.out.println(pageNum);
        for(int i = 0; i < pageNum; i++) {
            last = (start + pageSize) > total ? total : (start + pageSize);
            for(int e = start ; e < last; e++ ) {
                System.out.print(list.get(e) + " ");
            }
            System.out.println();
            start += pageSize;
        }
        System.out.println();
    }

}
