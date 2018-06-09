package com.wyl.cglib.lazyLoader.demo1;

import net.sf.cglib.proxy.LazyLoader;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class ConcreteClassLazyLoader implements LazyLoader {

    public Object loadObject() {
        System.out.println("LazyLoader loadObject() ...");
        PropertyBean bean = new PropertyBean();
        bean.setPropertyName("loaderNameB");
        bean.setPropertyValue(321);
        return bean;
    }
}
