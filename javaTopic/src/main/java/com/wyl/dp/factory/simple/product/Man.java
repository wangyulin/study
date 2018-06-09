package com.wyl.dp.factory.simple.product;

import com.wyl.dp.factory.simple.Product;

@Product(type="man")
public class Man implements Human {

    @Override
    public void say() {
        System.out.println("男人");
    }
}
