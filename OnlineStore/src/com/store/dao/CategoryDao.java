package com.store.dao;

import java.util.List;

import com.store.domain.Category;

public interface CategoryDao {
	
	/**
	 * save a kind of category
	 * @param c
	 */
	void save(Category c);

	/**
	 * get all the categories
	 * @return list
	 */
	List<Category> findAll();
	/**
	 * find a category by a primary id
	 * @param categoryId
	 * @return
	 */
	Category findById(String categoryId);

}
