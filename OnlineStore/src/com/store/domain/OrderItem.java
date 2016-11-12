package com.store.domain;

import java.io.Serializable;

public class OrderItem implements Serializable {
	
	
	private String id;
	private int quantity;
	private float price;
	private Product product;
	private Order order;
	
	public String getItemId() {
		return id;
	}
	public void setItemId(String itemId) {
		this.id = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
