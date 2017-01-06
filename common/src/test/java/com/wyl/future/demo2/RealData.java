package com.wyl.future.demo2;

import java.util.concurrent.Callable;

/**
 * Created by wangyulin on 06/01/2017.
 */
public class RealData implements Callable<String>{
    private String param;

    public RealData(String param) {
        this.param = param;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer ( );
        for (int i = 0; i < 10; i++) {
            sb.append ( param );
            Thread.sleep ( 100 );
        }
        return sb.toString ();
    }
}
