package com.store.dao;

import java.util.List;

import com.store.domain.Product;

public interface ProductDao {

	void save(Product p);

	Product findProductById(String productId);

	int getTotalRecordsNum();

	List getRecords(int startIndex, int pageSize);

	int getTotalRecordsNumByCategoryId(String categoryId);

	List getRecords(int startIndex, int pageSize, String categoryId);
}
