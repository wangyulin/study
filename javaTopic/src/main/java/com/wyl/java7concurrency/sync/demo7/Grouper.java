package com.wyl.java7concurrency.sync.demo7;

/**
 * Created by wangyulin on 23/08/2017.
 */
public class Grouper implements Runnable {

    private Results results;

    public Grouper(Results results){
        this.results = results;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.printf("Grouper: Processing results...\n");
        int data[] = results.getData();

        for (int number : data) {
            finalResult += number;
        }

        System.out.printf("Grouper: Total result: %d.\n", finalResult);
    }
}
