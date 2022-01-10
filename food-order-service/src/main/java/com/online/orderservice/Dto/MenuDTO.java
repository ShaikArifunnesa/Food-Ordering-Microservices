package com.online.orderservice.Dto;

import java.time.LocalDate;

public class MenuDTO {

	private Integer orderId;
	private Integer userId;
	private double amount;
	private LocalDate date;
	
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public MenuDTO(Integer orderId, Integer userId, double amount, LocalDate date) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
		this.date = date;
	}
	
	
}
