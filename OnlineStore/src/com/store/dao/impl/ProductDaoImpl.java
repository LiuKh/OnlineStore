package com.store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.store.dao.ProductDao;
import com.store.domain.Product;
import com.store.domain.Category;
import com.store.util.DBCPUtil;

public class ProductDaoImpl implements ProductDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	@Override
	public void save(Product p) {
		try {
			qr.update("insert into product (id,productName,seller,price,path,fileName,description,categoryId) values (?,?,?,?,?,?,?,?)", p.getId(),p.getProductName(),p.getSeller(),p.getPrice(),p.getPath(),p.getFileName(),p.getDescription(),p.getCategory().getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Product findProductById(String productId) {
		try {
			Product product = qr.query("select * from product where id=?", new BeanHandler<Product>(Product.class), productId);
			if(product != null){
				Category c = qr.query("select * from category where id=(select CategoryId from product where id=?)", new BeanHandler<Category>(Category.class), product.getId());
				product.setCategory(c);
			}
			return product;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public int getTotalRecordsNum() {
		try {
			Object obj = qr.query("select count(*) from product", new ScalarHandler(1));
			Long num = (Long)obj;
			return num.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List getRecords(int startIndex, int pageSize) {
		try {
			List<Product> list = qr.query("select * from product limit ?,?", new BeanListHandler<Product>(Product.class), startIndex, pageSize);
			if(list != null && list.size() != 0){
				for(Product product:list){
					Category c = qr.query("select * from category where id=(select categoryId from product where id=?)", new BeanHandler<Category>(Category.class), product.getId());
					product.setCategory(c);
				}
			}
			
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getTotalRecordsNumByCategoryId(String categoryId) {
		try {
			Object obj;
			obj = qr.query("select count(*) from product where categoryId=?", new ScalarHandler(1), categoryId);
			Long num = (Long)obj;
			return num.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public List getRecords(int startIndex, int pageSize, String categoryId) {
		try {
			List<Product> list;
			list = qr.query("select * from product where categoryId=? limit ?,?", new BeanListHandler<Product>(Product.class), categoryId, startIndex, pageSize);
			if(list != null && list.size() != 0){
				for(Product product : list){
					Category c = qr.query("select * from category where id=?", new BeanHandler<Category>(Category.class), categoryId);
					product.setCategory(c);
				}
			}
			
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
