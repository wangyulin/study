package com.wyl.dp.proxy;

class RealSubject implements Subject {
    public void request(){
        System.out.println("RealSubject");
    }
}
