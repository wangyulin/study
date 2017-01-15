package com.wyl.spring.demo.soundsystem.demo1;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wangyulin on 13/01/2017.
 */
//@Component
public class CDPlayer2 implements MediaPlayer {

    private CompactDisc compactDisc;

    @Autowired
    public void setCompactDisc(CompactDisc compactDisc) {
        this.compactDisc = compactDisc;
    }

    public void play() {
        compactDisc.play();
    }

}
