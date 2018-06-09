package com.wyl.chain;

/**
 * Created by wangyulin on 14/02/2018.
 */
public abstract class ChainHandler {

    public void execute(Chain chain) {
        handleProcess();
        chain.proceed();
    }

    protected abstract void handleProcess();
}
