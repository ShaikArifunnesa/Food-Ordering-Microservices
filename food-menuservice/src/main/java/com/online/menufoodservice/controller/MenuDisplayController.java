package com.online.menufoodservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.online.menufoodservice.dto.MenuDTO;
import com.online.menufoodservice.model.Menu;
import com.online.menufoodservice.service.MenuService;


@RestController
//@RequestMapping("/menu")
@Validated
public class MenuDisplayController {
	
	private static final Logger Log = LoggerFactory.getLogger(MenuDisplayController.class);
	
	@Value("${server.port}")
	int port;

	
	@Autowired
	MenuService menuService;
	
	//Save menuList
	@RequestMapping(value="/menus", method = RequestMethod.POST)
	public ResponseEntity<String> saveMenuDetails(@Valid @RequestBody MenuDTO menudto) {
		
		boolean menuResponse= menuService.saveMenu(menudto);
		
		if(menuResponse) {
	       Log.info("Item added to menu successfully");
		return new ResponseEntity<String>("Item added to the menu successfully ",HttpStatus.ACCEPTED);
	}
	else {
		Log.info("Item added to menu Unsuccessfully");
		return new ResponseEntity<String>("Item added to the menu Unsuccessfully ",HttpStatus.NOT_ACCEPTABLE);
	}
   }
	
	//Get Menu List
	 @RequestMapping(value = "/menus/getAll", method = RequestMethod.GET)
	    public ResponseEntity<List<Menu>> getMenus() {
		 List<Menu> list=new ArrayList<>();
		 
		 	Log.info("Get all the items added");
	        list=menuService.getAllMenu();
	        
	        return new ResponseEntity<List<Menu>>(list,HttpStatus.ACCEPTED);
	 }
	 
	 //Get Menu Item By Name
	 @RequestMapping(value = "/menus/{itemName}", method = RequestMethod.GET)
	 public ResponseEntity<Menu> getByItemName(@PathVariable String itemName)
	 {
		Menu menu=new Menu();
		Log.info("Get the item details for the entered name");
		menu=menuService.getByItemName(itemName);
		return new ResponseEntity<Menu>(menu,HttpStatus.ACCEPTED);
		 
	 }
	 
	 
	 //Delete Item 
	 @RequestMapping(value = "/menus/{itemId}", method = RequestMethod.DELETE)
	    public ResponseEntity<String> delete(@PathVariable Integer itemId) {
	
		 	Log.info("Delete the specific item for the given id");
	        menuService.deleteByItemId(itemId);
			return new ResponseEntity<String>("Item deleted from the menuList",HttpStatus.OK);

	    }
	 //Update Menu List
	 @RequestMapping(value = "/menus", method=RequestMethod.PUT)
		public ResponseEntity<String> updateMenu(@RequestBody  MenuDTO menuDto,@PathVariable Integer itemId)
		{  
		 menuService.updateMenu(itemId,menuDto); 
		 	Log.info("Update the existing items data");			
			return new ResponseEntity<String>("Item updated successfully", HttpStatus.ACCEPTED); 
		}
	 
	 	@GetMapping("/menus/port")
		public ResponseEntity<Integer> getPort() {
			return new ResponseEntity<Integer>(port, HttpStatus.OK);
		}


}
