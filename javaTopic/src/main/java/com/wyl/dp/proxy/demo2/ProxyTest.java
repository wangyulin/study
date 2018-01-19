package com.wyl.dp.proxy.demo2;

public class ProxyTest {

    public static void main(String[] args) {
        Service service = new UserServiceImpl();
        MyInvocatioHandler handler = new MyInvocatioHandler(service);
        Service serviceProxy = (Service)handler.getProxy();
        serviceProxy.add();
    }

}
