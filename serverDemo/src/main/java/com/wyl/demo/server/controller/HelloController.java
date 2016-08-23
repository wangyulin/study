package com.wyl.demo.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wyl.demo.server.module.Student;

@RequestMapping("/hello")
@RestController
public class HelloController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HelloController.class); 
	
	private static Map<String,String> map = new HashMap<String,String>();
	private static Map<String,Student> stuStore = new HashMap<String,Student>();
	static{
		map.put("v1","v1 String");
		map.put("v2","v2 String");
		map.put("v3","v3 String");
		map.put("v4","v4 String");
		
		Student stu1 = new Student("001","Mary",20);
		stuStore.put("001", stu1);
		Student stu2 = new Student("002","Tom",21);
		stuStore.put("002", stu2);
		Student stu3 = new Student("003","Jersey",28);
		stuStore.put("003", stu3);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	@ResponseBody
	public Object hello() {
		Map<String,String> result = new HashMap<String,String>();
		result.put("A", "1234");
		result.put("B", "9876");
		return result;
	}
	
	@RequestMapping(value = "/{firstName}", method = RequestMethod.GET)
	@ResponseBody
	public Object hello_v2(@PathVariable String firstName) {
		return "Hello "+ firstName;
	}
	
	@RequestMapping(value = "/{firstName}/{lastName}", method = RequestMethod.GET)
	@ResponseBody
	public Object hello_v3(@PathVariable String firstName, @PathVariable String lastName) {
		return "Hello "+ firstName + " " + lastName;
	}
	
	@RequestMapping(value = "/getVersion", method = RequestMethod.GET)
	@ResponseBody
    public String getProductMethod1(@RequestParam(value="version", defaultValue="v1") String version) {
        return map.get(version);
    }
	
	@RequestMapping(value = "/findOne", method = RequestMethod.POST)
	@ResponseBody
	public Student findOne(@RequestBody Student stu) {
		return stuStore.get(stu.getName());
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<Student> find() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Student> result = new ArrayList<Student>();
		for(String key : stuStore.keySet()) {
			result.add(stuStore.get(key));
		}
		return result;
	}
	
	@RequestMapping(value = "/findOne/v2", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Student> findOne_v2(@RequestBody Student stu) {
		Student result = stuStore.get(stu.getId());
		LOGGER.info("result : {}", result);
		return new ResponseEntity<Student>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Student> updateStu(@PathVariable String id, @RequestBody Student stu) {
		Student result = stuStore.get(id);
		if(result != null) {
			stuStore.put(id, stu);
			return new ResponseEntity<Student>(stuStore.get(id), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/getClassInfo/{clazz}", method = RequestMethod.GET)
	@ResponseBody
	public String getClassInfo(@PathVariable String clazz) {
		System.out.println(clazz);
		Class<?> onwClass;
		try {
			onwClass = Class.forName(clazz);
			return onwClass.getProtectionDomain().getCodeSource().getLocation().getPath();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}
	
}
