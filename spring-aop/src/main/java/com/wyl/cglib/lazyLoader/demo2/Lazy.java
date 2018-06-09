package com.wyl.cglib.lazyLoader.demo2;

import net.sf.cglib.proxy.LazyLoader;

/**
 * Created by wangyulin on 12/02/2018.
 */
class Lazy implements LazyLoader {
    public Object loadObject() {
        System.out.println("开始延迟加载!");
        TestBean bean = new TestBean();
        bean.setUserName("test");
        return bean;
    }
}
