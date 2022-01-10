package com.online.orderservice.service;

import java.util.List;


import com.online.orderservice.entity.OrderInfo;

public interface OrderInfoService {

	List<OrderInfo> getOrderByDate(Integer userId, String startDate, String endDate);
	
	void saveOrder(Integer userId, double amount);
	
}
