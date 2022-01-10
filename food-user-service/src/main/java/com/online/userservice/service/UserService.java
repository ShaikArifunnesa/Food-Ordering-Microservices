package com.online.userservice.service;



import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import com.online.userservice.Dto.Menu;
import com.online.userservice.Dto.OrderInfo;
import com.online.userservice.Dto.UserDTO;
import com.online.userservice.entity.User;

public interface UserService {

	boolean save(@Valid UserDTO userdto);
	
	boolean updateUser(Integer userId, UserDTO userDto);

	String deleteById(Integer userId);

	List<User> getAllUsers();

	String Doorder(Integer userId);

	ResponseEntity<List<OrderInfo>> getOrdersBydate(Integer userId, String startDate, String endDate);

	ResponseEntity<Menu> getItemByName(String itemName);

	String placeOrder(String itemName, Integer userId);

	ResponseEntity<String> saveOrder(Integer userId, double amount);

	

	

	



}
