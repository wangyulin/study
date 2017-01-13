package com.wyl.spring.demo.soundsystem.conf;

import com.wyl.spring.demo.soundsystem.CDPlayer;
import com.wyl.spring.demo.soundsystem.CompactDisc;
import com.wyl.spring.demo.soundsystem.MediaPlayer;
import com.wyl.spring.demo.soundsystem.SgtPeppers;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by wangyulin on 13/01/2017.
 */

@Configurable
@ComponentScan(basePackageClasses = {CompactDisc.class})
//@ComponentScan(basePackages = {"com.wyl.spring.demo.soundsystem"})
public class CDPlayConfig {

    @Bean
    public CompactDisc sgtPeppers() {
        return new SgtPeppers ();
    }

    @Bean
    public MediaPlayer mediaPlayer(CompactDisc compactDisc) {
        return new CDPlayer ( compactDisc );
    }

}
