package com.wyl.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class SampleClassDemo {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClassDemo.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...\n");
                return result;
            }
        });
        SampleClassDemo sample = (SampleClassDemo) enhancer.create();
        sample.hello1();
        sample.hello2();
    }

    public void hello1(){
        System.out.println("hello 1 world");
    }

    public void hello2() {
        System.out.println("hello 2 world");
    }

}
