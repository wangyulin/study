package com.wyl.spring.demo.soundsystem.demo2;

import com.wyl.spring.demo.soundsystem.demo1.CDPlayer;
import com.wyl.spring.demo.soundsystem.demo1.CompactDisc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by wangyulin on 14/01/2017.
 */

@Configuration
//@Import ( CDConfig.class )
public class CDPlayerConfig {

    @Bean
    public CDPlayer cdPlayer(CompactDisc compactDisc) {

        return new CDPlayer ( compactDisc );
    }

}
