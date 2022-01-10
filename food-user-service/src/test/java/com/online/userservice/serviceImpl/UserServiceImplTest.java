package com.online.userservice.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.online.userservice.Dto.Menu;
import com.online.userservice.Dto.UserDTO;
import com.online.userservice.entity.User;
import com.online.userservice.exception.EmaiIdExistException;
import com.online.userservice.exception.PhoneNumberExistException;
import com.online.userservice.exception.UserExistException;
import com.online.userservice.repository.UserRepository;
import com.online.userservice.serviceimpl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@Mock
	UserRepository userRepo;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	UserDTO userDto;
	User user;
	User savedUser;
	private Integer userId;
	
	@BeforeEach
	public void setUp() {
		
		userDto=new UserDTO();
		userDto.setUserName("Vasu");
		userDto.setEmailId("vasu@hcl.com");
		userDto.setPhoneNumber("123456789");
		userDto.setAddress("hyd");
		userDto.setEwallet(200.00);
		
		user=new User();
		user.setUserName("Jaanu");
		user.setEmailId("jaanu@hcl.com");
		user.setPhoneNumber("741258963");
		user.setAddress("Kerala");
		user.setEwallet(300.00);
		
		savedUser=new User();
		savedUser.setUserName("Jaanu");
		savedUser.setEmailId("jaanu@hcl.com");
		savedUser.setPhoneNumber("741258963");
		savedUser.setAddress("Kerala");
		savedUser.setEwallet(300.00);
		savedUser.setUserId(1);
	
	}
		@Test
		public void saveUserDataTest_Positive() {
				//context
				when(userRepo.save(any(User.class))).thenAnswer(i -> {
					User user=i.getArgument(0);
					user.setUserId(1);
					return user;
		});
				//event
				boolean result= userServiceImpl.save(userDto);
				
				//outcome
				assertTrue(result);
		}
		
		@Test
		public void UserDataTest() {
				//context
				when(userRepo.save(any(User.class))).thenAnswer(i -> {
					User user=i.getArgument(0);
					user.setUserId(1);
					return user;
		});
				//event
				boolean result= userServiceImpl.save(userDto);
				
				//outcome
				assertTrue(result);
		}
		
		@Test
		public void saveCustomerDataTest_Negative() {
				//context
				when(userRepo.save(any(User.class))).thenReturn(null) ;
				
				//event
				boolean result= userServiceImpl.save(userDto);
				
				//outcome
				assertFalse(result);
				verify(userRepo).save(any(User.class));
		}
		
		@Test
		public void getAllUserTest() {
			List<User> clist=new ArrayList<>();
			
			//context
			when(userRepo.findAll()).thenReturn(clist);

			//event
			List<User> result = userServiceImpl.getAllUsers();
			
			//outcome
			assertEquals(clist, result);
		}
		@Test
		public void deleteUserTest() {
			
			User user=new User();
			
			user.getUserId();
			
			userRepo.save(user);
			
			userRepo.deleteById(user.getUserId());	
			
		}
		
		@Test
		public void testUpdateUser() throws Exception {
		
			User user1=new User();
			user1.setUserId(userId);
			userRepo.findByUserIdEquals(user1.getUserId());
			userRepo.save(user1);
		}
		@Test
		public void userExsit() throws UserExistException{
			
			when(userRepo.save(any())).thenReturn(user);
			
			userServiceImpl.save(userDto);
			
			verify(userRepo).save(any());
		}
		@Test
		public void EmailExsit() throws EmaiIdExistException{
			
			when(userRepo.save(any())).thenReturn(user);
			
			userServiceImpl.save(userDto);
			
			verify(userRepo).save(any());
		}
		@Test
		public void phoneExsit() throws PhoneNumberExistException{
			
			when(userRepo.save(any())).thenReturn(user);
			
			userServiceImpl.save(userDto);
			
			verify(userRepo).save(any());
		}
		
		
	}
	

