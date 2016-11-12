package com.store.dao;

import java.util.List;

import com.store.domain.Function;
import com.store.domain.Role;
import com.store.domain.User;

public interface managementDao {
	
	public User login(String username, String password);

	public List<Role> findByUser(User user);

	public List<Function> findByRole(Role role);

	
}

