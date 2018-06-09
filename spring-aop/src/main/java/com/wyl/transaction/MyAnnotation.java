package com.wyl.transaction;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;

/**
 * Created by wangyulin on 14/02/2018.
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)  //可以通过反射读取注解
@Inherited
public @interface MyAnnotation {
    String value();
}
