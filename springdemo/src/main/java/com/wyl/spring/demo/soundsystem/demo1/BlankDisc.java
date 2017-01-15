package com.wyl.spring.demo.soundsystem.demo1;

/**
 * Created by wangyulin on 13/01/2017.
 */
public class BlankDisc implements CompactDisc {

    private String title;
    private String artist;

    public BlankDisc(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public void play() {
        System.out.println ("Playing " + title + " by " + artist);
    }

    @Override
    public void playTrack(int index) {

    }
}
