package com.wyl.cglib.fixedValue;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class Main {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,
                "/Users/wangyulin/workDir/miui/study/spring-aop/src/main/cglib");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new DemoMethodInterceptor());
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null)); //拦截test，输出Hello cglib
        //System.out.println(proxy.toString());
        //System.out.println(proxy.getClass());
        //System.out.println(proxy.hashCode());
        System.out.println(System.getProperty("java.vm.name"));
    }

    public static class DemoMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("CgLib is starting ...");
            return methodProxy.invokeSuper(o, objects);
        }
    }
}
