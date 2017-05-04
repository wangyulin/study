package com.wyl.spring.boot.demo.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;

/**
 * Created by wangyulin on 04/05/2017.
 */
public class MyTestInterceptor extends EmptyInterceptor {

    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        return false;
    }

}
