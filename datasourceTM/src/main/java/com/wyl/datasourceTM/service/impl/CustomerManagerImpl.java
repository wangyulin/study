package com.wyl.datasourceTM.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.wyl.datasourceTM.dao.CustomerDAO;
import com.wyl.datasourceTM.model.Customer;
import com.wyl.datasourceTM.service.CustomerManager;

public class CustomerManagerImpl implements CustomerManager {

	private CustomerDAO customerDAO;

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	@Transactional(rollbackFor={Exception.class})
	public void createCustomer(Customer cust) {
		customerDAO.create(cust);
	}

}
