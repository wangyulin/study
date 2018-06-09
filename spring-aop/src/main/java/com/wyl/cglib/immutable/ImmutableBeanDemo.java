package com.wyl.cglib.immutable;

import net.sf.cglib.beans.ImmutableBean;
import org.junit.Assert;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class ImmutableBeanDemo {

    public static void main(String[] args) {
        SampleBean bean = new SampleBean();
        bean.setValue("Hello world");
        SampleBean immutableBean = (SampleBean) ImmutableBean.create(bean); //创建不可变类
        System.out.println(immutableBean.getValue());
        Assert.assertEquals("Hello world",immutableBean.getValue());
        bean.setValue("Hello world, again"); //可以通过底层对象来进行修改
        System.out.println(immutableBean.getValue());
        Assert.assertEquals("Hello world, again", immutableBean.getValue());
        immutableBean.setValue("Hello cglib"); //直接修改将throw exception
    }
}
