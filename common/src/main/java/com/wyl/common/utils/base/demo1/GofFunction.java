package com.wyl.common.utils.base.demo1;

/**
 * Created by wangyulin on 28/12/2017.
 */
@FunctionalInterface
public interface GofFunction<T1,T2> {
    void execute(T1 t1,T2 t2);
}
