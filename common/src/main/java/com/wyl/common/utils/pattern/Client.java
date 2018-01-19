package com.wyl.common.utils.pattern;

/**
 * Created by wangyulin on 25/05/2017.
 * 测试程序：测试状态模式的代码
 * 状态模式多用在一个东西具有多个状态。状态切换的时候在传统上我们使用的是if结构，
 * 但是这种结构有着致命的问题，但还是同样的问题，后期不利于维护，尤其是在状态奇幻的时候需要
 * 更改原有代码违法开闭原则。
 */
public class Client {

    public static void main(String[] args) {
        HouseContext ctx = new HouseContext();
        ctx.setState(new FreeState());
        ctx.setState(new BookedState());
    }

}
