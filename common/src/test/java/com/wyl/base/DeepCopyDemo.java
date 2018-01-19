package com.wyl.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangyulin on 11/05/2017.
 */
public class DeepCopyDemo {

    public static void main(String[] args) {
        List<String> dst = new ArrayList<>();
        dst.add("123");
        dst.add("ABC");
        List<String> tmp = new ArrayList<>();
        //Collections.copy(dst, tmp);
        System.arraycopy(dst, 0, tmp, 0, dst.size());
        dst.clear();
        System.out.println(tmp);

    }

}
