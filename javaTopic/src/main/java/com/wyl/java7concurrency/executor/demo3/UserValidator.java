package com.wyl.java7concurrency.executor.demo3;


import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 29/08/2017.
 */
public class UserValidator {

    private String name;

    public UserValidator(String name) {
        this.name = name;
    }

    public boolean validate(String name, String password) {
        Random random = new Random();
        try {
            long duration = (long) (Math.random() * 10);
            System.out.printf("Validator %s: Validating a user during %d seconds\n", this.name, duration);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            return false;
        }
        return random.nextBoolean();
    }

    public String getName() {
        return this.name;
    }

}
