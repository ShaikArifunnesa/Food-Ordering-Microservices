package com.online.userservice.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.userservice.Dto.Menu;
import com.online.userservice.Dto.OrderInfo;
import com.online.userservice.Dto.UserDTO;
import com.online.userservice.entity.User;
import com.online.userservice.feign.MenuClient;
import com.online.userservice.repository.UserRepository;
import com.online.userservice.service.UserService;

@RestController

@Validated
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	MenuClient menuClient;
	
	@Autowired
	UserRepository userRepository;
	
	List<Menu> list;
	
	// Create a Logger with class name UserController
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value="/users", method = RequestMethod.POST)
	public ResponseEntity<String> saveUserDetails(@Valid @RequestBody UserDTO userdto) {
		
		boolean response=userService.save(userdto);
		if(response)
		{
		logger.info("User saved successfully ");
		return new ResponseEntity<String>("User saved successfully ",HttpStatus.ACCEPTED);
		}
		else {
		logger.info("User Data Save Unsccessfully");
		return new ResponseEntity<String>("User Data Save Unsccessfully",HttpStatus.NOT_ACCEPTABLE);
		}
   }

	 @RequestMapping(value = "/users", method = RequestMethod.DELETE)
	   public ResponseEntity<String> delete(@RequestParam Integer userId) {
	   
		 	userService.deleteById(userId);
		   // Call info method
		   logger.info("Returning user delete details with UserId " +userId+ " was deleted");
		   return new ResponseEntity<String>("user deleted successfuly",HttpStatus.OK);
		 	

 }
	   //Update User details
	   @RequestMapping(value = "/users/{userId}", method=RequestMethod.PUT)
	   public ResponseEntity<String> updateUser(@RequestBody UserDTO userDto,@PathVariable Integer userId){
		   
		   boolean updateUserResponse=userService.updateUser(userId,userDto);
		   if(updateUserResponse)
		   {
			   logger.info("User updated successfully");
			   return new ResponseEntity<String>("User updated successfully", HttpStatus.ACCEPTED);
		   }
		   else
		   {
			   logger.info("User updated Unsuccessfully");
			   return new ResponseEntity<String>("User updated Unsuccessfully", HttpStatus.BAD_REQUEST); 
		   }
		   
	   }
	   
	   @RequestMapping(value = "/users/getAll", method = RequestMethod.GET)
	   public ResponseEntity<List<User>> getUsers() {
	    List<User> userList=new ArrayList<>();
	
	       userList=userService.getAllUsers();
	       logger.info("Fetching All Stored User Data");
	       return new ResponseEntity<List<User>>(userList,HttpStatus.ACCEPTED);
	   }
 
	   		/**************** Feign ***********/
	   
	   @GetMapping("/feign/menus/getAll")
	   public ResponseEntity<List> getMenus(){
		   
		   logger.info("Get the list of Menuitems in menu service using feignclient");
		   return menuClient.getMenus();
		   
	   }
	   @RequestMapping(value="/feign/menus/{itemName}", method = RequestMethod.GET)
		public ResponseEntity<Menu> getItemByName(@PathVariable String itemName)
		{
		    logger.info("Get the item detail of given name");
			return userService.getItemByName(itemName);
		}
	   @GetMapping("/feign/menus/port")
	   public ResponseEntity<Integer> getPort() {
		   
			return menuClient.getPort();
		}
	   	@RequestMapping(value="/feign/saveOrder/{itemName}/user/{userId}",method=RequestMethod.POST)
		public String placeOrder(@PathVariable String itemName,@PathVariable Integer userId)
		{
			
			return userService.placeOrder(itemName,userId);
		}
	    
	   /*************feign-order*******/
	   @RequestMapping(value="/feign/doorder/user/{userId}",method=RequestMethod.POST)
		public String Doorder(@PathVariable Integer userId)
		{
		   logger.info("Order the stored item by specific user");
			return userService.Doorder(userId);
		}
		
	   	@RequestMapping(value="/feign/orderInfo/user/{userId}/sd/{startDate}/ed/{endDate}", method=RequestMethod.GET)
		public ResponseEntity<List<OrderInfo>> getOrdersBydate(@PathVariable Integer userId,@PathVariable String startDate,@PathVariable String endDate)
		{
			  logger.info("Get the history of orders between the entered dates");
			return userService.getOrdersBydate(userId, startDate, endDate);
		}
		
	  
}
