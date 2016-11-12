package com.store.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.store.dao.CustomerDao;
import com.store.domain.Customer;
import com.store.util.DBCPUtil;

public class CustomerDaoImpl implements CustomerDao {

	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());

	@Override
	public void save(Customer c) {
		try {
			qr.update("insert into customer (id,username,password,nickname,phone,email,address) values (?,?,?,?,?,?,?)", c.getId(), c.getUsername(), c.getPassword(), c.getNickname(), c.getPhone(), c.getEmail(), c.getAddress());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public Customer findById(String customerId) {
		try {
			return qr.query("select * from customer where id=?", new BeanHandler<Customer>(Customer.class), customerId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Customer find(String username, String password) {
		try {
			return qr.query("select * from customer where username=? and password=?", new BeanHandler<Customer>(Customer.class), username, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

}
