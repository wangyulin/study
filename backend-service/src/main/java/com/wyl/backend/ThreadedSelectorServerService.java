package com.wyl.backend;

import com.wyl.backend.service.Hello;
import com.wyl.backend.service.impl.HelloServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.Buffer;


public class ThreadedSelectorServerService {

    private static Logger logger = LoggerFactory.getLogger(ThreadedSelectorServerService.class);

	public static void main(String[] args) {
		try {
            /** 设置服务端口为 9527*/
            TNonblockingServerTransport transport = new TNonblockingServerSocket(9527);

            /** 定义处理器*/
            TProcessor helloProcessor = new Hello.Processor<Hello.Iface>(new HelloServiceImpl());
            //TProcessor processor_stu = new StudentService.Processor(new StudentServiceImpl());

            /** 定义传输工厂(默认使用的是：)*/
            TTransportFactory transportFactory = new TFramedTransport.Factory();

            /** 定义协议工厂(默认使用的是：TBinaryProtocol) */
            TJSONProtocol.Factory protocolFactory = new TJSONProtocol.Factory();

            /** 定义server参数对象*/
            TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(transport);

            /** 指定该server使用 的 传输，传输协议，业务处理器*/
            tArgs.processor(helloProcessor);
            tArgs.transportFactory(transportFactory);
            tArgs.protocolFactory(protocolFactory);

            TServer server = new TThreadedSelectorServer(tArgs);

            logger.info("Start server on port 9527...");

            /** 启动server */
            server.serve();

            Buffer buffer;

        } catch (TTransportException e) {
            e.printStackTrace();
        }
	}
}
