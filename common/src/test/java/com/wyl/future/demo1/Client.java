package com.wyl.future.demo1;

import java.util.concurrent.Future;

/**
 * Created by wangyulin on 06/01/2017.
 */
public class Client {
    public Data request (final String queryStr) {
        final FutureData future = new FutureData ();
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData ( queryStr );
                future.setRealData ( realData );
            }
        }.start ();
        return future;
    }

}
