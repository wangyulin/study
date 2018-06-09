package com.wyl.java7concurrency.threadmanagement.demo2;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 19/08/2017.
 */
public class Main {

    public static void main(String[] args) {
        FileSearch searcher = new FileSearch("/Users/wangyulin/workDir/miui/study", "FileSearch.java");
        Thread t = new Thread(searcher);
        t.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //t.interrupt();
    }

}
