package com.wyl.cglib.interfaces;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class CGLibFactory implements MethodInterceptor {
    // 声明目标类的成员变量
    private ISomeService target;

    public CGLibFactory() {
    }

    // 创建以目标类对象为参数的构造器,用于接收目标对象
    public CGLibFactory(ISomeService someService) {
        this.target = someService;
    }

    // 定义代理的生成方法,用于创建代理对象
    public ISomeService myCGLibCreator() {
        Enhancer enhancer = new Enhancer();
        // 为代理对象设置父类，即指定目标类
        enhancer.setSuperclass(ISomeService.class);
        /**
         * 设置回调接口对象 注意，只所以在setCallback()方法中可以写上this，
         * 是因为MethodIntecepter接口继承自Callback，是其子接口
         */
        enhancer.setCallback(this);
        return (ISomeService) enhancer.create();// create用以生成CGLib代理对象
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        if (method.toString().contains("eat")) {// 吃饭就不用律师代劳了，自己来
            return (String) method.invoke(target, args);// 反射，调用目标类的方法;
        }
        return "律师打官司，赢了";
    }

}
