package com.wyl.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by wangyulin on 12/02/2018.
 */
public class DaoClient {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,
                "/Users/wangyulin/workDir/miui/study/spring-aop/src/main/cglib");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        enhancer.setCallback(new DaoProxy());
        //enhancer.setCallbackFilter(new CallbackFilterImpl());

        Dao dao = (Dao)enhancer.create();

        dao.update();
        //dao.select();
    }

}
