package com.wyl.chain;

import java.util.List;

/**
 * Created by wangyulin on 14/02/2018.
 */
public class Chain {

    private List<ChainHandler> handlers;

    private int index = 0;

    public Chain(List<ChainHandler> handlers) {
        this.handlers = handlers;
    }

    public void proceed() {
        if(index >= handlers.size()) {
            return;
        }
        handlers.get(index++).execute(this);
    }
}
