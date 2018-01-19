package com.wyl.common.utils.pattern;

/**
 * Created by wangyulin on 25/05/2017.
 * 已经入住状态
 */
public class CheckedInState implements State {
    @Override
    public void handle() {
        System.out.println("已经入住");
    }
}
