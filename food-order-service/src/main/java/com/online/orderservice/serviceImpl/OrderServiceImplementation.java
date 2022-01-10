package com.online.orderservice.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.online.orderservice.Repo.OrderInfoRepository;
import com.online.orderservice.entity.OrderInfo;
import com.online.orderservice.exception.OrderInfoHistoryNotFoundException;
import com.online.orderservice.service.OrderInfoService;

@Service
public class OrderServiceImplementation implements OrderInfoService {
	
		
	@Autowired
	OrderInfoRepository orderRepository;  

	@Override
	public void saveOrder(Integer userId, double amount){
		
		OrderInfo order = new OrderInfo();
		order.setUserId(userId);
		order.setTotalPrice(amount);
		//order.setItemName(null);
		order.setDate(LocalDate.now());
		orderRepository.save(order);

	}

	@Override
	public List<OrderInfo> getOrderByDate(Integer userId,String fromDate,String toDate) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		LocalDate startDate = LocalDate.parse(fromDate, formatter);
		LocalDate endDate = LocalDate.parse(toDate, formatter);
		List<OrderInfo> list=orderRepository.getByDateAndUserId(startDate, endDate, userId);
		if(!list.isEmpty())
		{
			return list;
		}
		else
		{
			throw new OrderInfoHistoryNotFoundException("No orders In the given dates");
		}
	}
	
	
}