package com.wyl.cglib.lazyLoader.demo2;

import lombok.Data;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.LazyLoader;

/**
 * Created by wangyulin on 12/02/2018.
 */
@Data
class TB {
    LazyLoader lazy = new Lazy();
    private String cid;
    private TestBean bean;

    public TB() {
        cid = "1245454";
        bean = (TestBean) Enhancer.create(TestBean.class, lazy);
    }
}
