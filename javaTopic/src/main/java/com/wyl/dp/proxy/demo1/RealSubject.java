package com.wyl.dp.proxy.demo1;

class RealSubject implements Subject {
    public void request(){
        System.out.println("RealSubject");
    }
}
