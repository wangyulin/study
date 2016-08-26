package com.wyl.backend;

import com.wyl.backend.service.StudentService;
import com.wyl.backend.service.impl.StudentServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.wyl.backend.service.Hello;
import com.wyl.backend.service.impl.HelloServiceImpl;

public class ThriftServiceMain {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		try {
            TSSLTransportFactory.TSSLTransportParameters config = new TSSLTransportFactory.TSSLTransportParameters();
            config.setKeyStore("../.keystore","keyPass");
            // 设置服务端口为 9527 
            TServerSocket serverTransport = new TServerSocket(9527);
            // 关联处理器与 Hello 服务的实现
            //TProcessor processor = new Hello.Processor(new HelloServiceImpl());
            //TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            TProcessor stuProcessor = new StudentService.Processor(new StudentServiceImpl());
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(stuProcessor));

            System.out.println("Start server on port 9527...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
	}

}
