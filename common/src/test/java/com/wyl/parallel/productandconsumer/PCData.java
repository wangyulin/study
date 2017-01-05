package com.wyl.parallel.productandconsumer;

/**
 * Created by wangyulin on 04/01/2017.
 */
public final class PCData {

    private final int intData;

    public PCData(int d) {
        intData = d;
    }

    public PCData(String d) {
        intData = Integer.parseInt ( d );
    }

    public int getData() {
        return intData;
    }

    @Override
    public String toString() {
        return "date:" + intData;
    }
}
