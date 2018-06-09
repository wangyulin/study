package com.wyl.transaction;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * Created by wangyulin on 14/02/2018.
 */
public class Client {

    public static void main(String[] args) throws NoSuchMethodException {
        Class<UserServiceImpl> clazz = UserServiceImpl.class;

        Method methodOverride = clazz.getMethod("hello", new Class[] {});

        if(methodOverride.isAnnotationPresent(Transactional.class)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        Class<SubClass> clazz_2 = SubClass.class;

        if (clazz_2.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation cla = clazz_2.getAnnotation(MyAnnotation.class);
            System.out.println("子类继承到父类类上Annotation,其信息如下："+cla.value());
        } else {
            System.out.println("子类没有继承到父类类上Annotation");
        }

        // 实现抽象方法测试
        Method method = clazz_2.getMethod("abstractMethod", new Class[] {});
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation ma = method.getAnnotation(MyAnnotation.class);
            System.out.println("子类实现父类的abstractMethod抽象方法，继承到父类抽象方法中的Annotation,其信息如下："+ma.value());
        } else {
            System.out.println("子类实现父类的abstractMethod抽象方法，没有继承到父类抽象方法中的Annotation");
        }

        //覆盖测试
        Method methodOverride_2 = clazz_2.getMethod("doExtends", new Class[] {});
        if (methodOverride_2.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation ma = methodOverride_2.getAnnotation(MyAnnotation.class);
            System.out
                    .println("子类继承父类的doExtends方法，继承到父类doExtends方法中的Annotation,其信息如下："+ma.value());
        } else {
            System.out.println("子类继承父类的doExtends方法，没有继承到父类doExtends方法中的Annotation");
        }

        //继承测试
        Method method3 = clazz_2.getMethod("doHandle", new Class[] {});
        if (method3.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation ma = method3.getAnnotation(MyAnnotation.class);
            System.out.println("子类覆盖父类的doHandle方法，继承到父类doHandle方法中的Annotation,其信息如下："+ma.value());
        } else {
            System.out.println("子类覆盖父类的doHandle方法，没有继承到父类doHandle方法中的Annotation");
        }
    }
}
