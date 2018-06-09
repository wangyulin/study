package com.wyl.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class DaoProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println( "Method : " + method.getName() + "StartTime=[" + System.currentTimeMillis() + "]");
        /**
         * 直接使用invoke调用方法，会发生和java动态代理一样的无限循环调用
         * 会出现栈空间溢出
         */
        //Object result = method.invoke(obj, args);
        Object result = proxy.invokeSuper(object, args);
        System.out.println( "Method : " + method.getName() + "EndTime=[" + System.currentTimeMillis() + "]");
        return result;
    }
}
