package com.wyl.common.utils.pattern;

/**
 * Created by wangyulin on 25/05/2017.
 * 表征的空闲状态
 */
public class FreeState implements State {
    @Override
    public void handle() {
        System.out.println("空闲状态，可以入住");
    }
}
