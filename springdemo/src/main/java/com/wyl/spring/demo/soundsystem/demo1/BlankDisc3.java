package com.wyl.spring.demo.soundsystem.demo1;

import java.util.List;

/**
 * Created by wangyulin on 13/01/2017.
 */
public class BlankDisc3 implements CompactDisc {

    private String title;
    private String artist;
    private List<String> tracks;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    @Override
    public void play() {
        System.out.println ("Playing " + title + " by " + artist);
        if(tracks != null) {
            for (String track: tracks ) {
                System.out.println ("-Track : " + track);
            }
        }
    }

    @Override
    public void playTrack(int index) {

    }
}
