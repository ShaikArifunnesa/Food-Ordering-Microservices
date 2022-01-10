package com.online.userservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.online.userservice.Dto.OrderInfo;


@FeignClient("ORDERSERVICE")
public interface OrderClient {
	
	@PostMapping("/orderInfo/user/{userId}/amt/{amount}")
	public ResponseEntity<String> saveOrder(@PathVariable Integer userId,@PathVariable double amount);
	
	@GetMapping("/orderInfo/user/{userId}/sd/{startDate}/ed/{endDate}")
	public ResponseEntity<List<OrderInfo>> getOrdersBydate(@PathVariable Integer userId,@PathVariable String startDate,@PathVariable String endDate);

	
}

