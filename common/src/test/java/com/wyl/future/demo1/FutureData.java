package com.wyl.future.demo1;

/**
 * Created by wangyulin on 06/01/2017.
 */
public class FutureData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;
    public synchronized void setRealData( RealData realData) {
        if(isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll ();
    }
    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait ();
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
        }
        return realData.result;
    }
}
