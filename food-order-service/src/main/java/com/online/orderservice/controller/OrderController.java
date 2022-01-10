package com.online.orderservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import com.online.orderservice.service.OrderInfoService;
import com.online.orderservice.Dto.OrderDTO;
import com.online.orderservice.entity.OrderInfo;



@RestController
//@RequestMapping("/order")
@Validated
public class OrderController {
	
	private static final Logger log= LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderInfoService orderInfoService;
	
	@RequestMapping(value="/orderInfo/user/{userId}/amt/{amount}", method = RequestMethod.POST)
	public ResponseEntity<String> saveOrder(@PathVariable Integer userId,@PathVariable double amount){
		
		log.info("Order placed by specific user");
        orderInfoService.saveOrder(userId,amount);
        return new ResponseEntity<String>("Order saved",HttpStatus.ACCEPTED);
        
    }
	/*@RequestMapping(value="/orderInfo", method = RequestMethod.POST)
	public ResponseEntity<String> saveOrder(@RequestBody OrderDTO orderdto){
		
		orderInfoService.saveOrder(orderdto);
		return new ResponseEntity<String>("Order saved",HttpStatus.ACCEPTED);
		
	}*/
					
	@RequestMapping(value="/orderInfo/user/{userId}/sd/{startDate}/ed/{endDate}", method = RequestMethod.GET)
	public ResponseEntity<List<OrderInfo>> getOrdersBydate(@PathVariable Integer userId,@PathVariable String startDate,@PathVariable String endDate)
	{
		log.info("Get the order history between start and end dates");
		List<OrderInfo> list=orderInfoService.getOrderByDate(userId,startDate,endDate);
		return new ResponseEntity<List<OrderInfo>>(list, HttpStatus.OK);
	}
	
	
}
