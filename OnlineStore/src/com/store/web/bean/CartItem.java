package com.store.web.bean;

import java.io.Serializable;

import com.store.domain.Product;

public class CartItem implements Serializable {

	
	private Product product;
	private int numberOfProducts;
	private float item_money;
	

	public CartItem(Product product) {
		this.product = product;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public int getNumberOfProducts() {
		return numberOfProducts;
	}


	public void setNumberOfProducts(int numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}


	public float getItem_money() {
		return product.getPrice() * numberOfProducts;
	}


	public void setItem_money(float item_money) {
		this.item_money = item_money;
	}
	
}
