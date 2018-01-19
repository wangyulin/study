package com.wyl.base;

import java.io.File;

/**
 * Created by wangyulin on 11/05/2017.
 */
public class FileDemo {

    public static void main(String[] args) {
        String filePath = "/Users/wangyulin/work/order.tar.gz";
        File file = new File(filePath);
        System.out.println(file.getParent());
        System.out.println(file.getParentFile());
    }

}
