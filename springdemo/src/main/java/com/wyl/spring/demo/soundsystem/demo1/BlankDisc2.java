package com.wyl.spring.demo.soundsystem.demo1;

import java.util.List;

/**
 * Created by wangyulin on 13/01/2017.
 */
public class BlankDisc2 implements CompactDisc {

    private String title;
    private String artist;
    private List<String> tracks;

    public BlankDisc2(String title, String artist, List<String> tracks) {
        this.title = title;
        this.artist = artist;
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

    public void playTrack(int index) {
        if(tracks.size () - 1 >= index) {
            System.out.println (tracks.get ( index ));
        } else {
            System.err.println ( "ERROR index is not exists !" );
        }
    }
}
