package com.wyl.spring.demo.soundsystem.chapter4;

import com.wyl.spring.demo.soundsystem.demo1.BlankDisc2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyulin on 15/01/2017.
 */

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class TrackCounterConfig {

    @Bean
    public BlankDisc2 blankDisc2() {
        List<String> tracks = new ArrayList<> (  );
        tracks.add ( "With a Little Help from My Friends" );
        tracks.add ( "Fixing a Hole" );
        tracks.add ( "Getting Better" );
        BlankDisc2 blankDisc2 = new BlankDisc2 ( "Sgt. Pepper's Lonely Hearts Club Band","The Beatles", tracks );
        return blankDisc2;
    }

    @Bean
    public TrackCounter trackCounter() {
        return new TrackCounter ();
    }

}
