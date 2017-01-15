package com.wyl.spring.demo.soundsystem.demo1;

import org.springframework.stereotype.Component;

/**
 * Created by wangyulin on 13/01/2017.
 */

@Component
public class SgtPeppers implements CompactDisc {

    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println ("Playing " + title + " by " + artist);
    }
}
