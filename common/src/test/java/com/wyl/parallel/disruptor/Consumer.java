package com.wyl.parallel.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * Created by wangyulin on 04/01/2017.
 */
public class Consumer implements WorkHandler<PCData> {

    @Override
    public void onEvent(PCData pcData) throws Exception {
        System.out.println (Thread.currentThread ().getId () + ": Event: --" + pcData.get () * pcData.get () + "--");
    }
}
