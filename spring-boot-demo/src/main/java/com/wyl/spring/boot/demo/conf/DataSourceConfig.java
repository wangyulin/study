package com.wyl.spring.boot.demo.conf;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by wangyulin on 28/03/2017.
 */
@Configuration
@ConfigurationProperties(prefix="primary.datasource")
public class DataSourceConfig {

    @Value("${primary.datasource.url}")
    private String url;
    @Value("${primary.datasource.username}")
    private String userName;
    @Value("${primary.datasource.password}")
    private String password;
    @Value("${primary.datasource.driver-class-name}")
    private String driverClassname;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("settlement-service");
        String newPassword = textEncryptor.decrypt(password);
        this.password = newPassword;
        //this.password = password;
    }

    public String getDriverClassname() {
        return driverClassname;
    }

    public void setDriverClassname(String driverClassname) {
        this.driverClassname = driverClassname;
    }
}
