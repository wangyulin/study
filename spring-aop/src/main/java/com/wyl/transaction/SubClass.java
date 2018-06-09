package com.wyl.transaction;

/**
 * Created by wangyulin on 14/02/2018.
 */
public class SubClass extends AbstractParent {

    //子类实现父类的抽象方法
    @Override
    public void abstractMethod() {
        System.out.println("子类实现父类的abstractMethod抽象方法");
    }

    //子类覆盖父类的doExtends方法
    @Override
    public void doExtends(){
        System.out.println("子类覆盖父类的doExtends方法");
    }

}
