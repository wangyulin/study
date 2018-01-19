package com.wyl;

import com.wyl.springframework.alias.demo.SingletonBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.StandardEnvironment;

import java.util.Collections;
import java.util.Properties;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan
public class App {

    @Autowired
    private SingletonBean bean;

    @Autowired
    private ApplicationContext ctx;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(App.class);
    }

    @Test
    public void testScanBean() {
        SingletonBean bean = (SingletonBean) ctx.getBean("singletBean");
        StandardEnvironment environment = (StandardEnvironment) ctx.getBean("environment");
        Properties systemProperties = (Properties) ctx.getBean("systemProperties");
        //ctx.getBean("systemEnvironment");
        System.out.println( bean );
        //
        System.out.println(environment);
        System.out.println(systemProperties);
        System.out.println(systemProperties.getProperty("file.separator", "//"));
        System.out.println(systemProperties.getProperty("user.timezone", "Asia/Shanghai"));

    }
}
