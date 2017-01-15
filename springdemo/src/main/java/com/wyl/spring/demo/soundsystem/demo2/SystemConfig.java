package com.wyl.spring.demo.soundsystem.demo2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by wangyulin on 14/01/2017.
 */

@Configuration
@Import ( {CDPlayerConfig.class} ) //, CDConfig.class
@ImportResource("classpath:applicationContext2.xml")
public class SystemConfig {
}
