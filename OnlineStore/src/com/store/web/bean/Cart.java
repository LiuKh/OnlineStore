package com.store.web.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.store.domain.Product;

public class Cart implements Serializable {

	private Map<String, CartItem> map = new HashMap();
	private int totalNumberOfProducts;
	private float money;
	
	public int getTotalNumberOfProducts() {
		totalNumberOfProducts = 0;
		for(Map.Entry<String, CartItem> me : map.entrySet()){
			totalNumberOfProducts += me.getValue().getNumberOfProducts();
		}
		return totalNumberOfProducts;
	}
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setTotalNumberOfProducts(int totalNumberOfProducts) {
		this.totalNumberOfProducts = totalNumberOfProducts;
	}
	public float getMoney() {
		money = 0;
		for(Map.Entry<String, CartItem> me : map.entrySet()){
			money += me.getValue().getItem_money();
		}
		
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public void addProduct(Product p){
		if(map.containsKey(p.getId())){
			CartItem item = map.get(p.getId());
			item.setNumberOfProducts(item.getNumberOfProducts() + 1);
			map.put(p.getId(), item);
		}else{
			CartItem item = new CartItem(p);
			item.setNumberOfProducts(1);
			map.put(p.getId(), item);
		}
	}
}
