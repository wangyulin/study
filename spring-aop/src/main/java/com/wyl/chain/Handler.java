package com.wyl.chain;

import java.util.Objects;

/**
 * Created by wangyulin on 14/02/2018.
 */
public abstract class Handler {

    private Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public void execute() {
        handleProcess();
        if(Objects.nonNull(successor)) {
            successor.execute();
        }
    }

    protected abstract void handleProcess();
}
