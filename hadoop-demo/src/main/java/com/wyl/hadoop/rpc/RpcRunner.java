package com.wyl.hadoop.rpc;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.apache.hadoop.ipc.RPC.Server;

/**
 * Created by wangyulin on 12/03/2017.
 */
public class RpcRunner {
    /**
     * 定义一个rpc服务器端，监听RpcService类
     * @param args
     * @throws IOException
     * @throws HadoopIllegalArgumentException
     */
    public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
        //创建一个Rpc builder对象
        Builder builder = new RPC.Builder(new Configuration());
        //builder属性设置
        builder.setBindAddress("127.0.0.1")//设置地址，本机名或者ip
                .setPort(10000)//服务端口
                .setProtocol(RpcService.class)//监听的服务接口
                .setInstance(new RpcServiceImpl());//接口的实现对象

        //创建一个service
        Server servier = builder.build();
        servier.start();
    }

}