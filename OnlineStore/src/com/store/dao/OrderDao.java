package com.store.dao;

import java.util.List;

import com.store.domain.Customer;
import com.store.domain.Order;

public interface OrderDao {

	void save(Order o);

	Order findByOrderNum(String ordernum);
	
	List<Order> findByCustomerId(String customerId);

}
