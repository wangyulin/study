package com.wyl.demo.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	@ResponseBody
	public Object hello() {
		Map<String,String> result = new HashMap<String,String>();
		result.put("A", "1234");
		result.put("B", "9876");
		return result;
	}
	
}
