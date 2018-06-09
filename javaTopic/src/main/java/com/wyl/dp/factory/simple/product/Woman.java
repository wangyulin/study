package com.wyl.dp.factory.simple.product;

import com.wyl.dp.factory.simple.Product;

@Product(type="woman")
public class Woman implements Human {

    @Override
    public void say() {
        System.out.println("女人");
    }
}
