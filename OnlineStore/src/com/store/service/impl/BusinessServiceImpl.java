package com.store.service.impl;

import java.util.List;

import com.store.commons.Page;
import com.store.dao.CategoryDao;
import com.store.dao.CustomerDao;
import com.store.dao.OrderDao;
import com.store.dao.ProductDao;
import com.store.dao.managementDao;
import com.store.dao.impl.CategoryDaoImpl;
import com.store.dao.impl.CustomerDaoImpl;
import com.store.dao.impl.OrderDaoImpl;
import com.store.dao.impl.ProductDaoImpl;
import com.store.dao.impl.managementDaoImpl;
import com.store.domain.Customer;
import com.store.domain.Function;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.Product;
import com.store.domain.Category;
import com.store.domain.Role;
import com.store.domain.User;
import com.store.service.BusinessService;
import com.store.util.IdGenerator;

public class BusinessServiceImpl implements BusinessService {
	private CategoryDao cDao = new CategoryDaoImpl();
	private ProductDao pDao = new ProductDaoImpl();
	private CustomerDao cmDao = new CustomerDaoImpl();
	private OrderDao oDao = new OrderDaoImpl();
	private managementDao mDao = new managementDaoImpl();
	
	@Override
	public void addCategory(Category c) {
		c.setId(IdGenerator.generateGUID());
		cDao.save(c);
	}

	@Override
	public List<Category> findAllCategories() {
		return cDao.findAll();
	}

	@Override
	public Category findCategoryById(String CategoryId) {
		return cDao.findById(CategoryId);
	}

	@Override
	public void addProduct(Product p) {
		if(p == null)
			throw new IllegalArgumentException("no pruduct error");
		if(p.getCategory() == null)
			throw new IllegalArgumentException("no category");
		p.setId(IdGenerator.generateGUID());
		pDao.save(p);
	}

	@Override
	public Product findProductById(String productId) {
		return pDao.findProductById(productId);
	}

	@Override
	public Page findPruductPageRecords(String num) {
		
		int pageNum = 1;
		if(num != null && num.length() != 0){
			pageNum = Integer.parseInt(num);
		}
		
		int totalRecordsNum = pDao.getTotalRecordsNum();
		Page page = new Page(pageNum, totalRecordsNum);
		List records = pDao.getRecords(page.getStartIndex(), page.getPageSize());
		page.setRecords(records);
		
		return page;
	}

	@Override
	public Page findPruductPageRecords(String num, String categoryId) {
		
		int pageNum = 1;
		if(num != null && num.length() != 0){
			pageNum = Integer.parseInt(num);
		}
		
		int totalRecordsNum = pDao.getTotalRecordsNumByCategoryId(categoryId);
		Page page = new Page(pageNum, totalRecordsNum);
		List records = pDao.getRecords(page.getStartIndex(), page.getPageSize(), categoryId);
		page.setRecords(records);
		return page;
	}

	@Override
	public void addCustomer(Customer c) {
		c.setId(IdGenerator.generateGUID());
		cmDao.save(c);
	}

	@Override
	public Customer findCustomerById(String customerId) {
		return cmDao.findById(customerId);
	}

	@Override
	public Customer customerLogin(String username, String password) {
		return cmDao.find(username, password);
	}

	@Override
	public Order findOrderByOrderNum(String ordernum) {
		return oDao.findByOrderNum(ordernum);
	}

	@Override
	public List<Order> findOrderByCustomer(String customerId) {
		return oDao.findByCustomerId(customerId);
	}

	@Override
	public void addOrder(Order o) {
		if(o == null)
			throw new IllegalArgumentException("no order");
		if(o.getC() == null)
			throw new IllegalArgumentException("no customer");
		if(o.getItems() == null || o.getItems().size() == 0)
			throw new IllegalArgumentException("no items");
		oDao.save(o);
	}

	@Override
	public User userLogin(String username, String password) {
		return mDao.login(username, password);
	}

	@Override
	public List<Role> findRolesByUser(User user) {
		return mDao.findByUser(user);
	}

	@Override
	public List<Function> findFunctionByRole(Role role) {
		return mDao.findByRole(role);
	}
	
	
}
