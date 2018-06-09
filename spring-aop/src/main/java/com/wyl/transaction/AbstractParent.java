package com.wyl.transaction;

/**
 * Created by wangyulin on 14/02/2018.
 */
@MyAnnotation(value = "类名上的注解")
public abstract class AbstractParent {

    @MyAnnotation(value = "父类的abstractMethod方法")
    public abstract void abstractMethod();

    @MyAnnotation(value = "父类的doExtends方法")
    public void doExtends() {
        System.out.println(" ParentClass doExtends ...");
    }

    @MyAnnotation(value = "父类的doHandle方法")
    public void doHandle(){
        System.out.println(" ParentClass doHandle ...");
    }

}
