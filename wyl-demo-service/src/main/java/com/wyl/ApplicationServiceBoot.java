package com.wyl;

import com.google.common.eventbus.EventBus;
import com.wyl.utils.eventbus.EventListener;
import org.h2.server.web.WebServlet;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Hello world!
 *
 */
//@EnableCircuitBreaker
//@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
public class ApplicationServiceBoot {

    public static EventBus eventBus = new EventBus("test");

    public static void main( String[] args ) {
        EventListener listener = new EventListener();
        eventBus.register(listener);
        new SpringApplicationBuilder(ApplicationServiceBoot.class).web(true).run(args);
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        WebServlet webServlet = new WebServlet();
        ServletRegistrationBean registration = new ServletRegistrationBean(webServlet);
        registration.addUrlMappings("/h2-console/*");
        return registration;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
                @Override
                public void addCorsMappings(CorsRegistry corsRegistry) {
                    corsRegistry.addMapping("**").allowedOrigins("*").allowedMethods("*");
                }
        };
    }

}
