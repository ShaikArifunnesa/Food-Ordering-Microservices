package com.online.orderservice.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;

@Entity
public class OrderInfo{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ordersId")
	private Integer orderId;
	
	@NotNull(message = "userid cant be null")
	private Integer userId;
		
	private double totalPrice;
	
	private LocalDate date;
	
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

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

	public OrderInfo(Integer orderId, @NotNull(message = "userid cant be null") Integer userId, double totalPrice,
			LocalDate date) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.date = date;
		
	}

	public OrderInfo() {}
	
		
}

//	public OrderInfo() {
//    }
//
//	public OrderInfo(int userId, 
//			List<com.online.orderservice.entity.OrderItem> orderItemsList, double billAmount) {
//		super();
//		
//		this.userId = userId;
//		this.orderItemsList = orderItemsList;
//		this.billAmount =totalPrice(orderItemsList);
//	}
//	private double totalPrice(List<OrderItem> orderItemsList) {
//        double sum = 0;
//        for (OrderItem oi : orderItemsList) {
//           sum += oi.getPrice() * oi.getQuantity();
//        }
//        return sum;
//    }
//	
//}
