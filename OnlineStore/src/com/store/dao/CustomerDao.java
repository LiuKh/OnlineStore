package com.store.dao;

import com.store.domain.Customer;

public interface CustomerDao {

	void save(Customer c);

	Customer findById(String customerId);

	Customer find(String username, String password);

}
