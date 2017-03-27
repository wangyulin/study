package com.wyl.spring.boot.demo.conf;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import java.sql.SQLException;

import static com.wyl.spring.boot.demo.conf.SpringExtension.SPRING_EXTENSION_PROVIDER;

/**
 * Created by wangyulin on 24/03/2017.
 */
@Configuration
@ComponentScan
public class AkkaConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("akka-spring-boot-demo");
        SPRING_EXTENSION_PROVIDER.get(system).initialize(applicationContext);
        return system;
    }

}