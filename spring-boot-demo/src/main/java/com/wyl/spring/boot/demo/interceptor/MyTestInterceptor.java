package com.wyl.spring.boot.demo.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by wangyulin on 04/05/2017.
 */
public class MyTestInterceptor extends EmptyInterceptor {
    private ThreadLocal<String> test_param = new ThreadLocal<String>();
    final static Logger LOGGER = LoggerFactory.getLogger(MyTestInterceptor.class);

    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        test_param.set("100" + UUID.randomUUID());
        LOGGER.info("TAG_ entity: {}, id: {}, state : {}, propertyNames: {}, types: {}", new Object[]{entity, id, state, propertyNames, types});
        LOGGER.info("TAG_ entity: {}, id: {}, state : {}, propertyNames: {}, types: {}", new Object[]{entity, id, state, propertyNames, types});
        return false;
    }

    public String onPrepareStatement(String sql) {
        String test_value = (String) test_param.get();
        test_param.remove();
        LOGGER.info("TAG_ sql: {}, test_value : {}", new Object[]{sql, test_value});
        return sql;
    }

}
