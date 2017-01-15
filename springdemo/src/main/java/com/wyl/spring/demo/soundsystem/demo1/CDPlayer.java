package com.wyl.spring.demo.soundsystem.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangyulin on 13/01/2017.
 */
@Component
public class CDPlayer implements MediaPlayer {
    private CompactDisc cd;

    @Autowired
    public CDPlayer(CompactDisc compactDisc) {
        this.cd = compactDisc;
    }

    public void play() {
        cd.play();
    }

}
