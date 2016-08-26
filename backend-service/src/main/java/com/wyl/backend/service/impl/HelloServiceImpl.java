package com.wyl.backend.service.impl;

import org.apache.thrift.TException;

import com.wyl.backend.exception.CatchableException;
import com.wyl.backend.service.Hello;

public class HelloServiceImpl implements Hello.Iface {

    @Override
    public boolean helloBoolean(boolean para) throws TException {
        System.out.printf("hello true/false");
        return para;
    }

    @Override
    public int helloInt(int para) throws TException {
        System.out.println("hello times: " + para);
        return para;
    }

    @Override
    public String helloNull() throws CatchableException {
        System.out.println("hello null");
        throw new CatchableException(1,"Test CatchableException !");
        //return null;
    }

    @Override
    public String helloString(String para) throws TException {
        System.out.println("hello " + para);
        return para;
    }

    @Override
    public void helloVoid() throws TException {
        System.out.println("Hello World");
    }
}