package com.wyl.future.demo1;

/**
 * Created by wangyulin on 06/01/2017.
 */
public class RealData implements Data {
    protected final String result;
    public RealData(String param) {
        StringBuffer sb = new StringBuffer ( );
        for (int i = 0; i < 10; i++) {
            sb.append ( param );
            try {
                Thread.sleep ( 100 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
        result = sb.toString ();
    }
    @Override
    public String getResult() {
        return result;
    }
}
