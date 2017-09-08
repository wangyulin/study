package com.wyl.java7concurrency.customizing.demo3;

import java.util.Date;

/**
 * Created by wangyulin on 02/09/2017.
 */
public class MyThread extends Thread {

    private Date creationDate;
    private Date startTime;
    private Date finishDate;

    public MyThread(Runnable target, String name) {
        super(target, name);
        setCreationDate();
    }

    @Override
    public void run() {
        setStartTime();
        super.run();
        setFinishDate();
    }

    public void setCreationDate() {
        creationDate = new Date();
    }

    public void setStartTime() {
        startTime = new Date();
    }

    public void setFinishDate() {
        finishDate = new Date();
    }

    public long getExecutionTime() {
        return finishDate.getTime() - startTime.getTime();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getName());
        buffer.append(": ");
        buffer.append(" Creation Date: ");
        buffer.append(creationDate);
        buffer.append(" : Running time: ");
        buffer.append(getExecutionTime());
        buffer.append(" Milliseconds.");
        return buffer.toString();
    }

}
