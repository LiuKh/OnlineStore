package com.store.service;

import java.util.List;

import com.store.commons.Page;
import com.store.domain.Customer;
import com.store.domain.Function;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.Product;
import com.store.domain.Category;
import com.store.domain.Role;
import com.store.domain.User;

public interface BusinessService {
	/**
	 * add category 
	 * @param c
	 */
	void addCategory(Category c);
	/**
	 * find all the categories
	 * @return
	 */
	List<Category> findAllCategories();
	/**
	 * 
	 * @param CategoryId
	 * @return if no result, return null
	 */
	Category findCategoryById(String CategoryId);
	/**
	 * 
	 * @param b
	 */
	void addProduct(Product p);
	/**
	 * 
	 * @param BookId
	 * @return
	 */
	Product findProductById(String productId);
	/**
	 * 
	 * @param num
	 * @return
	 */
	Page findPruductPageRecords(String num);
	/**
	 * 
	 * @param num
	 * @param categoryId
	 * @return
	 */
	Page findPruductPageRecords(String num, String categoryId);
	
	void addCustomer(Customer c);
	
	Customer findCustomerById(String customerId);
	
	Customer customerLogin(String username, String password);
	
	Order findOrderByOrderNum(String ordernum);
	
	void addOrder(Order o);
	
	List<Order> findOrderByCustomer(String costumerId);
	
	public User userLogin(String username, String password);
	
	List<Role> findRolesByUser(User user);
	
	List<Function> findFunctionByRole(Role role);
}
