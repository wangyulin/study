package com.wyl.base;

import java.net.Socket;

/**
 * 检查服务是否可用，及检查端口是否正常监听。
 * Created by wangyulin on 13/02/2017.
 */
public class CheckoutPort {

    private static final String host = "wangyulin-test-host";

    public static void main(String[] args) {
        boolean result = checkoutPort(50071);
        System.out.println(result);
    }

    public static boolean checkoutPort(int port) {
        Socket socket;
        try {
            System.out.println("Looking for "+ port);
            socket = new Socket(host, port);
            System.out.println("There is a server on port " + port + " of " + host);
            if(null != socket) {
                socket.close();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
