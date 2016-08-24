package com.wyl.backend.service;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ThriftDemo {

	public static final int SERVER_PORT = 8090;
	
	@SuppressWarnings({ "rawtypes" })
	public static void main(String[] args) {
		try {
            // 设置调用的服务地址为本地，端口为 9527
            TTransport transport = new TSocket("localhost", 9527);
            transport.open();
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(transport);
            Hello.Client client = new Hello.Client(protocol);
            // 调用服务的 helloVoid 方法
            client.send_helloString("World !");

            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
	}

}
