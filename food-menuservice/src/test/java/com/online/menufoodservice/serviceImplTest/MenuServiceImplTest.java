package com.online.menufoodservice.serviceImplTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.online.menufoodservice.dto.MenuDTO;
import com.online.menufoodservice.exception.ItemExistException;
import com.online.menufoodservice.model.Menu;
import com.online.menufoodservice.repo.MenuRepository;
import com.online.menufoodservice.service.impl.MenuServiceImpl;


@ExtendWith(MockitoExtension.class)
public class MenuServiceImplTest 
{
		@Mock
		MenuRepository repository;

		@InjectMocks
		MenuServiceImpl menuServiceImpl;

		Menu menu;
		Menu savedMenu;
		MenuDTO menuDto;
		List<Menu> menulist;
		
		@BeforeEach
		void setUp() 
		{
			
			menuDto = new MenuDTO();
			menulist = new ArrayList<Menu>();
		    menuDto.setItemName("Biriyani");
		    menuDto.setItemPrice(400.00);
		    
		    menu = new Menu();
			menu.setItemId(1);
			menu.setItemName("kuruma");
			menu.setItemPrice(60.00);
			menulist.add(menu);
			
			savedMenu=new Menu();
			savedMenu.setItemId(1);
			savedMenu.setItemName("Pop-Corn");
			savedMenu.setItemPrice(20.0);
			
		}
		
		@Test
		@DisplayName("Save menu : Positive")
		void saveMenuTest_Positive() 
		{
			// context
			when(repository.save(any(Menu.class))).thenAnswer(i ->{
				Menu menu = i.getArgument(0);
				menu.setItemId(1);
				return menu;
			});
			//event
			boolean result = menuServiceImpl.saveMenu(menuDto);
			//outcome
			assertTrue(result);
		}

		@Test
		@DisplayName("Save Item : Negative")
		void SaveMenuTest_Negative() 
		{
			// context
					when(repository.save(any(Menu.class))).thenReturn(null);
					//event
					boolean result = menuServiceImpl.saveMenu(menuDto);
					//outcome
					assertFalse(result);
		}

		@Test
		@DisplayName("Get Menulist")
		void getAllMenuTest()  
		{
			List<Menu> menulist=new ArrayList<>();
			// context
			when(repository.findAll()).thenReturn(menulist);
			//event 
			List<Menu> menuItems = menuServiceImpl.getAllMenu(); 
			//outcome 
			assertEquals(menulist,menuItems); 
		}
		
		@Test
		@DisplayName("Get Particular Item By Name ")
		void getByItemNameTest()  
		{
			Menu menuItem=new Menu();
			
			menuItem.getItemName();
			
			repository.save(menuItem);
			
			repository.findByItemNameContaining(menuItem.getItemName());
		}	
		
		@Test
		public void deleteMenuTest() {
			
			Menu menuItem1=new Menu();
			
			menuItem1.getItemId();
			
			repository.save(menuItem1);
			
			repository.deleteById(menuItem1.getItemId());	
			
		}
		@Test
		public void ItemExsit() throws ItemExistException{
			
			when(repository.save(any())).thenReturn(menu);
			
			menuServiceImpl.saveMenu(menuDto);
			
			verify(repository).save(any());
		}
		@Test
		public void updateMenu() {
			
			Menu menuItem1=new Menu();
			Integer itemId = null;
			menuItem1.setItemId(itemId);
			repository.findByItemIdEquals(menuItem1.getItemId());
			repository.save(menuItem1);
			
		}
}