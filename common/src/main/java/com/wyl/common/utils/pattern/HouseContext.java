package com.wyl.common.utils.pattern;

/**
 * Created by wangyulin on 25/05/2017.
 * 房间上下文，持有状态成员变量。
 */
public class HouseContext {

    private State state;

    public HouseContext() {

    }

    public void setState(State state) {
        System.out.println("修改状态");
        this.state = state;
        state.handle();
    }
}