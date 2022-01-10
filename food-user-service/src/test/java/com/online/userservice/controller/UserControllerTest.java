package com.online.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.online.userservice.Dto.UserDTO;
import com.online.userservice.entity.User;
import com.online.userservice.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;
	
	UserDTO userdto;
	private Integer userId;
	
	@BeforeEach
	public void setUp() {
		userdto=new UserDTO();
		userdto.setUserName("siri");
		userdto.setEmailId("abc@gmail.com");
		userdto.setPhoneNumber("123456789");
		userdto.setAddress("hyd");
		userdto.setEwallet(500.00);
	}
	@Test
	@DisplayName("Save user Data:Positive")
	public void saveUserDataTest_Positive() {
				//context
				when(userService.save(userdto))
				.thenReturn(true);

				//event
				ResponseEntity<String> result = userController.saveUserDetails(userdto);

				//outcome
				assertEquals("User saved successfully ", result.getBody());
				assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
}

		@Test
		@DisplayName("Save user Data: Negative")
		public void saveUserDataTest_Negative() {
					//context
					when(userService.save(userdto))
					.thenReturn(false);

					//event
					ResponseEntity<String> result = userController.saveUserDetails(userdto);
					
					//outcome
					assertEquals("User Data Save Unsccessfully", result.getBody());
					assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
			}
		@Test
		@DisplayName("Delete user with ID")
		public void deleteUserTest() {
					
					//context
					when(userService.deleteById(userId)).thenReturn("user deleted");

					//event
					ResponseEntity<String> result = userController.delete(userId);
					
					//outcome
					assertEquals("user deleted successfuly",result.getBody());
					assertEquals(HttpStatus.OK, result.getStatusCode());
			}
		@Test
		@DisplayName("update User")
		public void updateUserTest() {
					
					
					//context
					when(userService.updateUser(userId,userdto)).thenReturn(true);

					//event
					ResponseEntity<String> result = userController.updateUser(userdto,userId);
					
					//outcome
					assertEquals("User updated successfully",result.getBody());
					assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
			}
		@Test
		@DisplayName("update User Negative")
		public void updateUserTest_Negative() {
					
					
					//context
					when(userService.updateUser(userId,userdto)).thenReturn(false);

					//event
					ResponseEntity<String> result = userController.updateUser(userdto,userId);
					
					//outcome
					assertEquals("User updated Unsuccessfully",result.getBody());
					assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
			}
		@Test
		@DisplayName("Get All Users")
		public void getAllUsers() {
			List<User> userList=new ArrayList<>();
			
			//context
			when(userService.getAllUsers()).thenReturn(userList);
			
			//event
			ResponseEntity<List<User>> response=userController.getUsers();
			
			//outcome
			assertEquals(userList,response.getBody());
			assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
			
		}
	
}
