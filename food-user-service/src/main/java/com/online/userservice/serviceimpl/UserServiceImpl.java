package com.online.userservice.serviceimpl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.online.userservice.Dto.Menu;
import com.online.userservice.Dto.OrderInfo;
import com.online.userservice.Dto.UserDTO;
import com.online.userservice.entity.OrderList;
import com.online.userservice.entity.User;

import com.online.userservice.exception.EmaiIdExistException;
import com.online.userservice.exception.PhoneNumberExistException;
import com.online.userservice.exception.UserExistException;
import com.online.userservice.exception.WalletAmountException;
import com.online.userservice.feign.MenuClient;
import com.online.userservice.feign.OrderClient;
import com.online.userservice.repository.OrderListRepository;
import com.online.userservice.repository.UserRepository;
import com.online.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	MenuClient menuClient;
	
	@Autowired
	OrderClient orderClient;
	
	@Autowired
	OrderListRepository orderlistRepository;

	@Override
	public boolean save(@Valid UserDTO userdto) {
		Optional<User> optionalUser=userRepo.findByUserName(userdto.getUserName());
		if(optionalUser.isPresent())
		{
			throw new UserExistException("This user already exist");
		}
		Optional<User> optionalEmailId=userRepo.findByEmailId(userdto.getEmailId());
		if(optionalEmailId.isPresent()) {
			throw new EmaiIdExistException("This EmailId already exist");
		}

		Optional<User> optionalPhoneNumber=userRepo.findByPhoneNumber(userdto.getPhoneNumber());
		if(optionalPhoneNumber.isPresent()) {
			throw new PhoneNumberExistException("This PhoneNumber already exist");
		}
		
		User user=new User();
		BeanUtils.copyProperties(userdto, user);
		User savedUser=userRepo.save(user);
		if(savedUser != null) return true;
		
		return false;
	
	
	}
	@Override
	public List<User> getAllUsers() {
		 List<User> userList=new ArrayList<>();
		Iterator iterator=userRepo.findAll().iterator();
		while(iterator.hasNext())
		{
			User user1=new User();
			BeanUtils.copyProperties(iterator.next(), user1);
			userList.add(user1);
		}
		return userList;
	}
	@Override
	public String deleteById(Integer userId) {
		
		userRepo.deleteById(userId);
		return "user deleted";
	}
	
	@Override
	public boolean updateUser(Integer userId, UserDTO userDto){

	User user=userRepo.findByUserIdEquals(userId);
	BeanUtils.copyProperties(userDto, user);
	User updatedUser =  userRepo.save(user);
	if(updatedUser != null){
		
		return true;
	}
	else{
		
		return false;
	}
}
	@Override
	public ResponseEntity<Menu> getItemByName(String itemName) {
		
		return menuClient.getItemByName(itemName);
	}
	
	List<Menu> list = new ArrayList<Menu>();
	
	public String placeOrder(String itemName, Integer userId)
	{
		OrderList ol=new OrderList();
		list.add(getItemByName(itemName).getBody());
		
		Menu menu = getItemByName(itemName).getBody();
		ol.setPrice(menu.getItemPrice());
		ol.setItemName(itemName);
		ol.setUserId(userId);
		orderlistRepository.save(ol);
		return "Order Saved Successfully: " + itemName;
		}
	@Override
	public String Doorder(Integer userId) {
		
		Optional<User> user =  userRepo.findById(userId);
		
		double walletAmount = user.get().getEwallet();
		double billAmount = processCart(list);
		
		double balance = walletAmount - billAmount;
		user.get().setEwallet(balance);
		
		userRepo.save(user.get());
		saveOrder(userId,billAmount);
		return "Thank you for ordering ";
	}
	
	@Override
	public ResponseEntity<String> saveOrder(@PathVariable Integer userId,@PathVariable double amount) 
	{
		return orderClient.saveOrder(userId, amount);
	}
	
	public double processCart(List<Menu> list)
	{
		double amount = 0.0;
		if(!list.isEmpty())
		{
			for(Menu items:list)
			{
				amount = amount + items.getItemPrice();
			}
		}
		return amount;
	}

	public ResponseEntity<List<OrderInfo>> getOrdersBydate(@PathVariable Integer userId,@PathVariable String startDate,@PathVariable String endDate)
	{
		return orderClient.getOrdersBydate(userId, startDate, endDate);
	}
		

}
