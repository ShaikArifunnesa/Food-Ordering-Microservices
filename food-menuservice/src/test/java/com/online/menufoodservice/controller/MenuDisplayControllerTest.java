package com.online.menufoodservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
import com.online.menufoodservice.dto.*;
import com.online.menufoodservice.model.Menu;
import com.online.menufoodservice.service.MenuService;

@ExtendWith(MockitoExtension.class)
public class MenuDisplayControllerTest
{
	@Mock
	MenuService menuService;
	
	@InjectMocks
	MenuDisplayController menuDisplayController;
	
	MenuDTO menudto;
	
	private Integer itemId;
	
	@BeforeEach
	public void setUp(){
		menudto=new MenuDTO();
		menudto.setItemName("Biriyani");
		menudto.setItemPrice(200.00);
	}
	@Test
	public void saveMenuTest_positive(){
		when( menuService.saveMenu(menudto)).thenReturn(true);
		
		ResponseEntity<String> result=menuDisplayController.saveMenuDetails(menudto);
		
		assertEquals("Item added to the menu successfully ",result.getBody());
		assertEquals(HttpStatus.ACCEPTED,result.getStatusCode());
	}
	@Test
	public void saveMenuTest_negitive(){
		when( menuService.saveMenu(menudto)).thenReturn(false);
		
		ResponseEntity<String> result=menuDisplayController.saveMenuDetails(menudto);
		
		assertEquals("Item added to the menu Unsuccessfully ",result.getBody());
		assertEquals(HttpStatus.NOT_ACCEPTABLE,result.getStatusCode());
	}
	
	@Test
	public void deleteTest(){
		when(menuService.deleteByItemId(itemId)).thenReturn("Item deleted from the menuList");
		
		 ResponseEntity<String> response=menuDisplayController.delete(itemId);
		
		 assertEquals("Item deleted from the menuList",response.getBody());
		 assertEquals(HttpStatus.OK,response.getStatusCode());
		
	}
	
	@Test
	public void getMenusTest()
	{
		List<Menu> mlist=new ArrayList<>();
		
		when(menuService.getAllMenu()).thenReturn(mlist);
		
		ResponseEntity<List<Menu>> result=menuDisplayController.getMenus();
	
		assertEquals(mlist,result.getBody());
		assertEquals(HttpStatus.ACCEPTED,result.getStatusCode());
	}

	
	@Test
	public void updateMenuTest(){
		
		when( menuService.updateMenu(itemId,menudto)).thenReturn("Item updated successfully");
		
		ResponseEntity<String> result=menuDisplayController.updateMenu(menudto,itemId);
		
		assertEquals("Item updated successfully", result.getBody());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}
}