package com.wyl.spring.demo.soundsystem.beanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        if (bean instanceof PostProcessorBean) {
            System.out.println("PostProcessorBean Bean initializing");
            PostProcessorBean pb = (PostProcessorBean) bean;

            System.out.println("username:" + pb.getUsername());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        if (bean instanceof PostProcessorBean) {
            System.out.println("PostProcessorBean Bean initialized");
            PostProcessorBean pb = (PostProcessorBean) bean;

            System.out.println("username:" + pb.getUsername());
        }
        return bean;
    }
}
