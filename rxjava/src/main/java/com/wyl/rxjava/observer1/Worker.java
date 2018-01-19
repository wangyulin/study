package com.wyl.rxjava.observer1;

/**
 * Created by wangyulin on 22/06/2017.
 */
public class Worker implements Observer, DisplayWork {


    private String name;
    private String des;

    public Worker(String name, String des) {
        super();
        this.name = name;
        this.des = des;
    }


    @Override
    public void display() {
        System.out.println("我在" + des + "了！");
    }


    @Override
    public void notifyPerson() {
        System.out.println("老板来了，" + name + "好好干活啦！");


    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
