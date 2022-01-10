package com.online.userservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer foodCartId;
	private Integer userId;
	private String itemName;
	private double price;
	public Integer getFoodCartId() {
		return foodCartId;
	}
	public void setFoodCartId(Integer foodCartId) {
		this.foodCartId = foodCartId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public OrderList(Integer foodCartId, Integer userId, String itemName, double price) {
		super();
		this.foodCartId = foodCartId;
		this.userId = userId;
		this.itemName = itemName;
		this.price = price;
	}
	
	public OrderList() {
		
	}
	
	
}
