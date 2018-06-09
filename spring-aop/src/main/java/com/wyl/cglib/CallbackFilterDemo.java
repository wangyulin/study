package com.wyl.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class CallbackFilterDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyClass.class);
        enhancer.setCallbacks(new Callback[] {new MethodInterceptorImpl(), NoOp.INSTANCE});
        enhancer.setCallbackFilter(new CallbackFilterImpl());

        MyClass myClass = (MyClass) enhancer.create();

        myClass.method1();
        myClass.method2();
    }

    private static class CallbackFilterImpl implements CallbackFilter {

        @Override
        public int accept(Method method) {
            if (method.getName().startsWith("method"))
                return 0;
            else
                return 1;
        }
    }

    private static class MethodInterceptorImpl implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args,
                                MethodProxy proxy) throws Throwable {
            System.out.println("Before invoke " + method);
            Object result = proxy.invokeSuper(obj, args);
            System.out.println("After invoke " + method);
            return result;
        }
    }
}

class MyClass {
    public final void method1() {
        System.out.println("MyClass.method1()");
    }

    public void method2() {
        System.out.println("MyClass.method2()");
    }
}