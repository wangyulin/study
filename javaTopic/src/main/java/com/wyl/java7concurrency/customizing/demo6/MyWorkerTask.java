package com.wyl.java7concurrency.customizing.demo6;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by wangyulin on 07/09/2017.
 */
public abstract class MyWorkerTask extends ForkJoinTask<Void> {

    private String name;

    public MyWorkerTask(String name) {
        this.name = name;
    }

    @Override
    public Void getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Void value) {

    }

    @Override
    protected boolean exec() {
        Date startDate = new Date();
        compute();
        Date finishedDate = new Date();
        long diff = finishedDate.getTime() - startDate.getTime();
        System.out.printf("MyWorkerTask: %s: %d Millisends to complete.\n", name, diff);
        return true;
    }

    public String getName() {
        return name;
    }

    protected abstract void compute();

}
