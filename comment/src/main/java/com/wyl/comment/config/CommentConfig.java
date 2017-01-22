package com.wyl.comment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by wangyulin on 21/01/2017.
 */

@Configuration
@PropertySource("classpath:application.properties")
public class CommentConfig {

    @Value("${spring.datasource.url}")
    private String jdbcURL ;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;


    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer () {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
