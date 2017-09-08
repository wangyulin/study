package com.wyl.java7concurrency.collectionframework.demo3;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class Event implements Comparable<Event> {

    private int thread;
    private int priority;

    public Event(int thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public int getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Event e) {
        if(this.priority > e.getPriority()) {
            return -1;
        } else if(this.priority < e.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }
}
