package com.wyl.spring.demo.soundsystem.demo1.conf;

import com.wyl.spring.demo.soundsystem.demo1.CDPlayer;
import com.wyl.spring.demo.soundsystem.demo1.CompactDisc;
import com.wyl.spring.demo.soundsystem.demo1.MediaPlayer;
import com.wyl.spring.demo.soundsystem.demo1.SgtPeppers;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by wangyulin on 13/01/2017.
 */

@Configurable
//@ComponentScan(basePackageClasses = {CompactDisc.class})
@ComponentScan(basePackages = {"com.wyl.spring.demo.soundsystem"})
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
