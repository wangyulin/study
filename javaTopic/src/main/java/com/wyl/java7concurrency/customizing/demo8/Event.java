package com.wyl.java7concurrency.customizing.demo8;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class Event implements Comparable<Event> {

    private String thread;

    private int priority;

    public Event(String thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public String getThread() {
        return this.thread;
    }

    public int getPriority() {
        return this.priority;
    }

    @Override
    public int compareTo(Event e) {
        if(this.priority > e.getPriority()) {
            return -1;
        } else if (this.priority < e.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }
}
