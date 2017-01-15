package com.wyl.spring.demo.soundsystem.chapter3.demo1;

import com.wyl.spring.demo.soundsystem.demo1.BlankDisc;
import com.wyl.spring.demo.soundsystem.demo1.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by wangyulin on 14/01/2017.
 */

@Configuration
@PropertySource ( value = {"classpath:blankdisc.properties"})
public class ExpressConfig {

    @Autowired
    Environment env;

    @Bean
    public CompactDisc blankDisc() {
        return new BlankDisc (
                env.getProperty ( "disc.title" ),
                env.getProperty ( "disc.artist" ) );
    }

}
