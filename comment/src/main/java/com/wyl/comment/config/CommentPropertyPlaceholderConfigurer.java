package com.wyl.comment.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

/**
 * Created by wangyulin on 21/01/2017.
 */
@Configuration
@PropertySource(name="contextProperties", ignoreResourceNotFound=true, value={"classpath:application.properties"})
public class CommentPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final String URL = "spring.datasource.url";
    private static final String CLASSNAME = "spring.datasource.driver-class-name";
    private static final String USERNAME = "spring.datasource.username";
    private static final String PASSWORD = "spring.datasource.password";// sceporta数据库加密之后的密码

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) {
        String password = props.getProperty(PASSWORD);
        props.setProperty(URL, "jdbc:mysql://wangyulin-test-host:3306/test?characterEncoding=utf-8");
        props.setProperty(CLASSNAME, "com.mysql.jdbc.Driver");
        props.setProperty(USERNAME, "root");
        props.setProperty(PASSWORD, "123456");
        System.out.println("111");
        super.processProperties(beanFactoryToProcess, props);
    }

}
