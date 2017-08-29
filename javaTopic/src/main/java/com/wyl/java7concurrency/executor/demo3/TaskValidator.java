package com.wyl.java7concurrency.executor.demo3;

import java.util.concurrent.Callable;

/**
 * Created by wangyulin on 29/08/2017.
 */
public class TaskValidator implements Callable<String> {

    private UserValidator validator;
    private String name;
    private String password;

    public TaskValidator(UserValidator validator, String name, String password) {
        this.validator = validator;
        this.name = name;
        this.password = password;
    }

    @Override
    public String call() throws Exception {
        if(! validator.validate(name, password)) {
            System.out.printf("%s: The user has not been found\n", validator.getName());
            throw new Exception("Error validatiing user");
        }

        System.out.printf("%s: The user has been found\n", validator.getName());
        return validator.getName();
    }
}
