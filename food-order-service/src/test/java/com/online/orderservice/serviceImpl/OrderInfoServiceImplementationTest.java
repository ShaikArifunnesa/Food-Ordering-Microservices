package com.online.orderservice.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.online.orderservice.Repo.OrderInfoRepository;
import com.online.orderservice.entity.OrderInfo;
import com.online.orderservice.exception.OrderInfoHistoryNotFoundException;


@ExtendWith(MockitoExtension.class)
public class OrderInfoServiceImplementationTest
{
	@Mock
	OrderInfoRepository orderinfoRepository;

	@InjectMocks
	OrderServiceImplementation orderServiceImplementation;

	OrderInfo orderinfo;
	List<OrderInfo> List;

	@BeforeEach
	void setUp() throws Exception 
	{
		orderinfo = new OrderInfo();
		orderinfo.setOrderId(1);
		orderinfo.setUserId(1);
		orderinfo.setTotalPrice(200.00);
		orderinfo.setDate(LocalDate.now());
		List = new ArrayList<OrderInfo>();
		List.add(orderinfo);
	}

	@Test
	void GetOrdersBydate_Test() 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		LocalDate startDate = LocalDate.parse("2022-01-01", formatter);
		LocalDate endDate = LocalDate.parse("2022-01-06", formatter);
		// context
		when(orderinfoRepository.getByDateAndUserId(startDate,endDate,1)).thenReturn(List);
		//event
		List<OrderInfo> result = orderServiceImplementation.getOrderByDate(1,"2022-01-01","2022-01-06");
		//outcome
		assertEquals(List, result);
	}
	
	@Test
	public void userExsit() throws OrderInfoHistoryNotFoundException{
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setUserId(1);
		orderInfo.setTotalPrice(200.00);
		
		when(orderinfoRepository.save(any())).thenReturn(orderinfo);
		
		orderServiceImplementation.saveOrder(orderInfo.getUserId(), orderInfo.getTotalPrice());
		
		verify(orderinfoRepository).save(any());
	}
	
}