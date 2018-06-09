package com.wyl.spring.demo.soundsystem.other;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

    public static void main(String args[]) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextOther.xml");
        Animal a1 = context.getBean("cat1", Animal.class);
        a1.sayHello();
        Animal a2 = context.getBean("dog1", Animal.class);
        a2.sayHello();
    }

}
