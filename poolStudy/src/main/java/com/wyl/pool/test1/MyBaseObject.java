package com.wyl.pool.test1;

/**
 * Created by wangyulin on 8/17/16.
 */
public class MyBaseObject {

    /** 记录从池中取出次数 */
    private int num;
    private boolean active;

    public MyBaseObject(){
        active = true;
        double it = Math.random();
        num = (int)(it * 1000);
        System.out.println("new BaseObject");
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getNum() {
        return num;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "MyBaseObject{" +
                "num=" + num +
                ", active=" + active +
                '}';
    }
}
