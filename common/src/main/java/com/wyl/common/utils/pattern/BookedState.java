package com.wyl.common.utils.pattern;

/**
 * Created by wangyulin on 25/05/2017.
 * 已经预定状态
 */
public class BookedState implements State {

    @Override
    public void handle() {
        System.out.println("已经预定，等待入住");
    }

}
