package com.wyl.java7concurrency.collectionframework.demo5;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyulin on 01/09/2017.
 */
public class DelayedItem<T> implements Delayed {

    private T t;
    private long liveTime ;
    private long removeTime;

    public DelayedItem(T t,long liveTime){
        this.setT(t);
        this.liveTime = liveTime;
        this.removeTime = TimeUnit.NANOSECONDS.convert(liveTime, TimeUnit.NANOSECONDS) + System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long ret = unit.convert(removeTime - System.nanoTime(), unit); //
        System.out.printf("----> %s : %d \n", t, ret);
        return ret;
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == null) return 1;
        if (o == this) return  0;
        if (o instanceof DelayedItem){
            DelayedItem<T> tmpDelayedItem = (DelayedItem<T>)o;
            if (this.liveTime > tmpDelayedItem.liveTime ) {
                return 1;
            }else if (this.liveTime == tmpDelayedItem.liveTime) {
                return 0;
            }else {
                return -1;
            }
        }
        long diff = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return diff > 0 ? 1:diff == 0? 0:-1;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
    @Override
    public int hashCode(){
        return t.hashCode();
    }

    @Override
    public boolean equals(Object object){
        if (object instanceof DelayedItem) {
            return object.hashCode() == hashCode() ?true:false;
        }
        return false;
    }
}
