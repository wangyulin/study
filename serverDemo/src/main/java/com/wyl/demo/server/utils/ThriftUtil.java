package com.wyl.demo.server.utils;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.wyl.backend.service.Hello;

public class ThriftUtil {
	
	ThreadLocal<TSocket> socketThreadLocalSafe = new ThreadLocal<TSocket>();
	
	public ThriftUtil(){
	
	}
	
	public static Hello.Client getClient() {
		try {
            // 设置调用的服务地址为本地，端口为 9527
            TTransport transport = new TSocket("localhost", 9527);
            transport.open();
            // 设置传输协议为 TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(transport);
            Hello.Client client = new Hello.Client(protocol);
            return client;
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
	}

}
