package com.wyl.dp.proxy.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class MyInvocatioHandler implements InvocationHandler {
    private Object target;

    public MyInvocatioHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-----before-----");
        Object result = method.invoke(target, args);
        System.out.println("-----end-----");
        return result;
    }

    /** 生成代理对象 */
    public Object getProxy() {
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(MyInvocatioHandler.class.getClassLoader(), interfaces, this);
    }
}
