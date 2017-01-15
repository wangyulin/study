package com.wyl.spring.demo.soundsystem.chapter4;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyulin on 15/01/2017.
 */

@Aspect
public class TrackCounter {

    public Map<Integer, Integer> trackCounts =
            new HashMap<> (  );

    @Pointcut ("execution(* com.wyl.spring.demo.soundsystem.demo1.BlankDisc2.playTrack(int)) " +
    "&& args(trackNumber)")
    public void trackPlayed(int trackNumber){}

    @Before ( "trackPlayed(trackNumber)" )
    public void countTrack(int trackNumber) {
        int currentCount = getPlayCount ( trackNumber );
        trackCounts.put ( trackNumber, currentCount + 1 );
    }

    public int getPlayCount(int tracknumber) {
        return trackCounts.containsKey ( tracknumber ) ?
                trackCounts.get ( tracknumber ) : 0;
    }

}
