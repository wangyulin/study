package com.wyl.java7concurrency.sync.demo6;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 23/08/2017.
 */
public class Participant implements Runnable {

    private Videoconference conference;

    private String name;

    public Participant(Videoconference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    @Override
    public void run() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        conference.arrive(name);
    }
}
