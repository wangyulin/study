package com.wyl.springframework.alias.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by wangyulin on 19/11/2017.
 */
@Component(value = "singletBean")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Data
public class SingletonBean {

    private String name = "Hello World !";

    public SingletonBean(){
        System.out.println("--SingletonBean--");
    }

}
