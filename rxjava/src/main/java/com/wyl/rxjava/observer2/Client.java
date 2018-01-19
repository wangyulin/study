package com.wyl.rxjava.observer2;

/**
 * Created by wangyulin on 22/06/2017.
 */
public class Client {
    public static void main(String[] args) {
        //首先定义一个观察者去观察
        BossSubject sub = new BossSubject();

        //定义张三，写代码
        Worker zs = new Worker("张三", "写代码");
        //告诉观察者，我需要知道老板什么时候来的
        //sub.registerObserver(zs);
        sub.addObserver(zs);

        //定义李四，管后勤
        Worker ls = new Worker("李四", "管后勤");
        //告诉观察者，我需要知道老板什么时候来的
        //sub.registerObserver(ls);
        sub.addObserver(ls);

        //定义王五，写代码
        Worker ww = new Worker("王五", "秘书");
        //告诉观察者，我需要知道老板什么时候来的
        //sub.registerObserver(ww);
        sub.addObserver(ww);

        //这时候，老板来了，告诉所有的人，老板来了
        //这个没有设置changed
        System.out.println("jdk changed not config");
        sub.notifyObservers();
        System.out.println("jdk changed config");
        sub.setChanged();
        sub.notifyAllObserver();
    }
}
