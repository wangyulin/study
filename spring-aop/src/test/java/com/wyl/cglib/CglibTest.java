package com.wyl.cglib;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class CglibTest {

    @Test
    public void testCglib() {
        DaoProxy daoProxy = new DaoProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        enhancer.setCallback(daoProxy);

        Dao dao = (Dao)enhancer.create();
        dao.update();
        dao.select();
    }

}
