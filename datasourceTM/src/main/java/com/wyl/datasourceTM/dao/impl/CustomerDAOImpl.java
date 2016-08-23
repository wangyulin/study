package com.wyl.datasourceTM.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.wyl.common.utils.Logger;
import com.wyl.datasourceTM.dao.CustomerDAO;
import com.wyl.datasourceTM.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	static Logger log = Logger.getLogger(CustomerDAOImpl.class); 
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void create(Customer customer) {
		String queryCustomer = "insert into Customer (id, name) values (?,?)";
		String queryAddress = "insert into Address (id, address,country) values (?,?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(queryCustomer, new Object[] { customer.getId(),customer.getName() });
		log.info("Begin Inserted into Customer Table ... ");
		jdbcTemplate.update(queryAddress, new Object[] { customer.getId(),
				customer.getAddress().getAddress(),
				customer.getAddress().getCountry() });
		log.info("Inserted into Address Table Successfully ... ");
	}

}
