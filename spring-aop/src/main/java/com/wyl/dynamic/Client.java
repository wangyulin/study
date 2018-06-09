package com.wyl.dynamic;

import com.wyl.pattern.RealSubject;
import com.wyl.pattern.Subject;

import java.lang.reflect.Proxy;

/**
 * Created by wangyulin on 10/02/2018.
 */
public class Client {

    public static void main(String[] args) {
        /** 生成代理类class文件的开关选项*/
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        System.out.println("JdkProxySubject is starting ... ");
        Subject subject = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(),
                new Class[]{Subject.class}, new JdkProxySubject(new RealSubject()));
        subject.hello();
        subject.request();
    }

}
