package com.wyl.parallel.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by wangyulin on 04/01/2017.
 */
public class PCdataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
