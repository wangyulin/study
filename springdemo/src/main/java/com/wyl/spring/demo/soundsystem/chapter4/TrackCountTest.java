package com.wyl.spring.demo.soundsystem.chapter4;

import com.wyl.spring.demo.soundsystem.demo1.BlankDisc2;
import com.wyl.spring.demo.soundsystem.demo1.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangyulin on 15/01/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TrackCounterConfig.class)
public class TrackCountTest {

    @Autowired
    private CompactDisc CompactDisc;

    @Autowired
    private TrackCounter trackCounter;

    @Test
    public void test1() {
        CompactDisc.playTrack ( 0 );
        CompactDisc.playTrack ( 0 );
        CompactDisc.playTrack ( 2 );
        CompactDisc.playTrack ( 1 );
        System.out.println (trackCounter.trackCounts);
    }

}
