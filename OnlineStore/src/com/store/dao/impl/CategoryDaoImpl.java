package com.store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.store.dao.CategoryDao;
import com.store.domain.Category;
import com.store.util.DBCPUtil;

public class CategoryDaoImpl implements CategoryDao {
	
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	
	@Override
	public void save(Category c) {
		try {
			qr.update("insert into category (id,name,description) values (?,?,?)", c.getId(), c.getName(), c.getDescription());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Category> findAll() {
		try {
			return qr.query("select * from category", new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Category findById(String categoryId) {
		try {
			return qr.query("select * from category where id=?", new BeanHandler<Category>(Category.class), categoryId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
