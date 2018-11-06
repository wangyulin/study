package com.wyl.conf;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.wyl.utils.eventbus.EventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;


/**
 * @Auther: wangyulin
 * @Date: 2018/11/5 23:40
 * @Description:
 */
@Configuration
public class Config {

    @Bean
    public EventListener eventListener() {
        return new EventListener();
    }

    @Bean
    public EventBus eventBus(EventListener listener) {
        EventBus bus = new AsyncEventBus(Executors.newSingleThreadExecutor());
        bus.register(listener);
        return bus;
    }

}
