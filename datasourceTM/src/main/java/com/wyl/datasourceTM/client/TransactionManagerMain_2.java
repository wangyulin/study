package com.wyl.datasourceTM.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wyl.common.utils.Logger;
import com.wyl.datasourceTM.model.Address;
import com.wyl.datasourceTM.model.Customer;
import com.wyl.datasourceTM.service.CustomerManager;
import com.wyl.datasourceTM.service.impl.CustomerManagerImpl;

public class TransactionManagerMain_2 {

	static Logger log = Logger.getLogger(TransactionManagerMain_2.class);  
	
	private static final int id = 12;
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"file:///Users/wangyulin/workDir/miui/study/datasourceTM/src/main/resources/applicationContext_2.xml");

		CustomerManager customerManager = ctx.getBean("customerManager",CustomerManagerImpl.class);
		log.info("开始保存数据 ... ");
		Customer cust = createDummyCustomer();
		try {
			customerManager.createCustomer(cust);
			log.info("数据保存成功 ！");
		} catch (Exception e) {
			log.error("数据保存异常 ！");
		}
		
		ctx.close();
	}
	
	private static Customer createDummyCustomer() {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setName("Pankaj");
		Address address = new Address();
		address.setId(id);
		address.setCountry("India");
		// setting value more than 20 chars, so that SQLException occurs
		address.setAddress("Albany Dr, San Jose, CA 95129");
		customer.setAddress(address);
		return customer;
	}

}
