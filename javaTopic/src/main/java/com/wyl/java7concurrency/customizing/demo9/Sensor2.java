package com.wyl.java7concurrency.customizing.demo9;

/**
 * Created by wangyulin on 08/09/2017.
 */
public class Sensor2 implements Runnable {

    private ParkingCounter counter;

    public Sensor2(ParkingCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.carIn();
        counter.carOut();
        counter.carOut();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
    }
}
