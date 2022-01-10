package com.online.orderservice.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

	import com.online.orderservice.entity.OrderInfo;
	import com.online.orderservice.service.OrderInfoService;

	@ExtendWith(MockitoExtension.class)
	public class OrderControllerTest {
			@Mock
			OrderInfoService orderinfoService;
			
			@InjectMocks
			OrderController orderController;
			
			OrderInfo orderinfo;
			List<OrderInfo> ordersList;

			@BeforeEach
			void setUp() throws Exception 
			{
				orderinfo = new OrderInfo();
				orderinfo.setOrderId(1);
				orderinfo.setUserId(1);
				orderinfo.setTotalPrice(300.00);
				orderinfo.setDate(LocalDate.now());
				ordersList = new ArrayList<OrderInfo>();
				ordersList.add(orderinfo);
			}

			@Test
			void GetOrdersBydate_Test() 
			{
				// context
				when(orderinfoService.getOrderByDate(1,"2022-01-01","2022-01-08")).thenReturn(ordersList);
				//event
				ResponseEntity<List<OrderInfo>> result = orderController.getOrdersBydate(1,"2022-01-01","2022-01-08");
				//outcome
				assertEquals(ordersList, result.getBody());
				assertEquals(HttpStatus.OK, result.getStatusCode());
			}

	}


