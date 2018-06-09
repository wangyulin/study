package com.wyl.cglib.lazyLoader.demo1;

import lombok.Data;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by wangyulin on 12/02/2018.
 */
@Data
public class LoaderBean {
    private String loaderName;
    private int loaderValue;
    private PropertyBean propertyBean;

    public LoaderBean() {
        this.loaderName = "loaderNameA";
        this.loaderValue = 123;
        this.propertyBean = createPropertyBean();
    }

    protected PropertyBean createPropertyBean() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PropertyBean.class);
        return (PropertyBean) enhancer.create(PropertyBean.class, new ConcreteClassLazyLoader());
    }
}
