package com.wyl.datasourceTM.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wyl.datasourceTM.model.Address;
import com.wyl.datasourceTM.model.Customer;
import com.wyl.datasourceTM.service.CustomerManager;
import com.wyl.datasourceTM.service.impl.CustomerManagerImpl;

public class TransactionManagerMain_1 {

	private static final int id = 11;
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"file:///Users/wangyulin/workDir/miui/study/datasourceTM/src/main/resources/applicationContext_1.xml");

		CustomerManager customerManager = ctx.getBean("customerManager",CustomerManagerImpl.class);

		Customer cust = createDummyCustomer();
		customerManager.createCustomer(cust);

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
