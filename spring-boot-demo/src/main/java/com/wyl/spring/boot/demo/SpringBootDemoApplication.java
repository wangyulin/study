package com.wyl.spring.boot.demo;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * SpringBootDemoApplication
 *
 */
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SpringBootDemoApplication {

    public static void main( String[] args ) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        WebServlet webServlet = new WebServlet();
        ServletRegistrationBean registration = new ServletRegistrationBean(webServlet);
        registration.addUrlMappings("/h2-console/*");
        return registration;
    }
}
