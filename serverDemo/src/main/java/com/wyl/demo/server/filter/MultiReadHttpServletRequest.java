package com.wyl.demo.server.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import com.wyl.demo.server.controller.HelloController;

public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
	
	private final static Logger LOGGER = LoggerFactory.getLogger("access");
	@SuppressWarnings("unused")
	private final static Logger CONSOLE_LOGGER = LoggerFactory.getLogger(HelloController.class); 
	
	private String _body;

	public MultiReadHttpServletRequest(HttpServletRequest request) throws IOException {
		super(request);
		
		HttpServletRequest req = (HttpServletRequest) request;
        StringBuffer url = req.getRequestURL();
        Enumeration<String> headerNames = req.getHeaderNames();
        Map<String,String> headers = new HashMap<String,String>();
        while(headerNames.hasMoreElements()) {
        	String name = headerNames.nextElement();
        	headers.put(name, req.getHeader(name));
        }
        
        if(HttpMethod.POST.toString().equalsIgnoreCase(req.getMethod()) 
        		|| HttpMethod.PUT.toString().equalsIgnoreCase(req.getMethod())) {
        	/** 处理POST、PUT请求的body数据 */
        	_body = "";
    		BufferedReader bufferedReader = request.getReader();
    		String line;
    		while ((line = bufferedReader.readLine()) != null) {
    			_body += line;
    		}
    		LOGGER.info("\n\tURL : {}\n\tHeaders : {}\n\tMethod : {}\n\tBody : {}\n",url, headers, req.getMethod(), _body.replaceAll("\\s*", ""));
        	
        } else if("GET".equalsIgnoreCase(req.getMethod())) {
        	/** 处理GET请求的数据 */
        	LOGGER.info("\n\tURL : {}\n\tHeaders : {}\n\tMethod : {}\n\t",url, headers, req.getMethod());
        }
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(_body.getBytes());
		return new ServletInputStream() {
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}
}