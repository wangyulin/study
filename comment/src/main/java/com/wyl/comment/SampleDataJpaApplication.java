package com.wyl.comment;


import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by wangyulin on 20/01/2017.
 */
@SpringBootApplication
public class SampleDataJpaApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleDataJpaApplication.class, args);
    }

    @Bean
    public PropertyPlaceholderConfigurer createPropertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        Resource resource = new ClassPathResource("application.properties");

        propertyPlaceholderConfigurer.setLocation(resource);

        return propertyPlaceholderConfigurer;
    }

}
