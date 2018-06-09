package com.wyl.other.wyl.hadoop.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by wangyulin on 12/03/2017.
 */
public class RpcClientController {
    /**
     * 客户端获取RPC代理访问远程服务器
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //1.代理接口 2.双边版本号 3.服务器地址 4.conf对象
        RpcService serviceImpl = RPC.getProxy(RpcService.class, 100, new InetSocketAddress("127.0.0.1", 10000),new Configuration());
        String result = serviceImpl.login("wo", "123");
        System.out.println(result);
    }

}