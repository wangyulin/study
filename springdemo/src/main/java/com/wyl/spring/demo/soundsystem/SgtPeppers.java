package com.wyl.spring.demo.soundsystem;

import com.wyl.spring.demo.soundsystem.CompactDisc;
import org.springframework.stereotype.Component;

/**
 * Created by wangyulin on 13/01/2017.
 */

@Component
public class SgtPeppers implements CompactDisc {
    private String tilte = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    public void play() {
        System.out.println("Playing " + tilte + " by " + artist);
    }
}